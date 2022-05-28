package io.cody.all_relationships.rest;

import io.cody.all_relationships.model.NeedsBillingAddressDTO;
import io.cody.all_relationships.service.NeedsBillingAddressService;
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
@RequestMapping(value = "/api/needsBillingAddresss", produces = MediaType.APPLICATION_JSON_VALUE)
public class NeedsBillingAddressResource {

    private final NeedsBillingAddressService needsBillingAddressService;

    public NeedsBillingAddressResource(
            final NeedsBillingAddressService needsBillingAddressService) {
        this.needsBillingAddressService = needsBillingAddressService;
    }

    @GetMapping
    public ResponseEntity<List<NeedsBillingAddressDTO>> getAllNeedsBillingAddresss() {
        return ResponseEntity.ok(needsBillingAddressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeedsBillingAddressDTO> getNeedsBillingAddress(
            @PathVariable final Long id) {
        return ResponseEntity.ok(needsBillingAddressService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createNeedsBillingAddress(
            @RequestBody @Valid final NeedsBillingAddressDTO needsBillingAddressDTO) {
        return new ResponseEntity<>(needsBillingAddressService.create(needsBillingAddressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNeedsBillingAddress(@PathVariable final Long id,
            @RequestBody @Valid final NeedsBillingAddressDTO needsBillingAddressDTO) {
        needsBillingAddressService.update(id, needsBillingAddressDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteNeedsBillingAddress(@PathVariable final Long id) {
        needsBillingAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
