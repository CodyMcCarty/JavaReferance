package io.cody.all_relationships.rest;

import io.cody.all_relationships.model.XDTO;
import io.cody.all_relationships.service.XService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/xs", produces = MediaType.APPLICATION_JSON_VALUE)
public class XResource {

    private final XService xService;

    public XResource(final XService xService) {
        this.xService = xService;
    }

    @GetMapping
    public ResponseEntity<List<XDTO>> getAllXs() {
        return ResponseEntity.ok(xService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<XDTO> getX(@PathVariable final Long id) {
        return ResponseEntity.ok(xService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createX(@RequestBody @Valid final XDTO xDTO) {
        return new ResponseEntity<>(xService.create(xDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateX(@PathVariable final Long id,
            @RequestBody @Valid final XDTO xDTO) {
        xService.update(id, xDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteX(@PathVariable final Long id) {
        xService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
