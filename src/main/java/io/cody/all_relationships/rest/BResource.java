package io.cody.all_relationships.rest;

import io.cody.all_relationships.model.BDTO;
import io.cody.all_relationships.service.BService;
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
@RequestMapping(value = "/api/bs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BResource {

    private final BService bService;

    public BResource(final BService bService) {
        this.bService = bService;
    }

    @GetMapping
    public ResponseEntity<List<BDTO>> getAllBs() {
        return ResponseEntity.ok(bService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BDTO> getB(@PathVariable final Long id) {
        return ResponseEntity.ok(bService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createB(@RequestBody @Valid final BDTO bDTO) {
        return new ResponseEntity<>(bService.create(bDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateB(@PathVariable final Long id,
            @RequestBody @Valid final BDTO bDTO) {
        bService.update(id, bDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteB(@PathVariable final Long id) {
        bService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
