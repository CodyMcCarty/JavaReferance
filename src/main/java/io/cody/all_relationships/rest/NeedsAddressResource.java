package io.cody.all_relationships.rest;

import io.cody.all_relationships.model.NeedsAddressDTO;
import io.cody.all_relationships.service.NeedsAddressService;
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
@RequestMapping(value = "/api/needsAddresss", produces = MediaType.APPLICATION_JSON_VALUE)
public class NeedsAddressResource {

    private final NeedsAddressService needsAddressService;

    public NeedsAddressResource(final NeedsAddressService needsAddressService) {
        this.needsAddressService = needsAddressService;
    }

    @GetMapping
    public ResponseEntity<List<NeedsAddressDTO>> getAllNeedsAddresss() {
        return ResponseEntity.ok(needsAddressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeedsAddressDTO> getNeedsAddress(@PathVariable final Long id) {
        return ResponseEntity.ok(needsAddressService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createNeedsAddress(
            @RequestBody @Valid final NeedsAddressDTO needsAddressDTO) {
        return new ResponseEntity<>(needsAddressService.create(needsAddressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNeedsAddress(@PathVariable final Long id,
            @RequestBody @Valid final NeedsAddressDTO needsAddressDTO) {
        needsAddressService.update(id, needsAddressDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteNeedsAddress(@PathVariable final Long id) {
        needsAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
