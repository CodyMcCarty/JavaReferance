package io.cody.all_relationships.rest;

import io.cody.all_relationships.model.YDTO;
import io.cody.all_relationships.service.YService;
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
@RequestMapping(value = "/api/ys", produces = MediaType.APPLICATION_JSON_VALUE)
public class YResource {

    private final YService yService;

    public YResource(final YService yService) {
        this.yService = yService;
    }

    @GetMapping
    public ResponseEntity<List<YDTO>> getAllYs() {
        return ResponseEntity.ok(yService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<YDTO> getY(@PathVariable final Long id) {
        return ResponseEntity.ok(yService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createY(@RequestBody @Valid final YDTO yDTO) {
        return new ResponseEntity<>(yService.create(yDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateY(@PathVariable final Long id,
            @RequestBody @Valid final YDTO yDTO) {
        yService.update(id, yDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteY(@PathVariable final Long id) {
        yService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
