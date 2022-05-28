package io.cody.all_relationships.rest;

import io.cody.all_relationships.model.HubDTO;
import io.cody.all_relationships.service.HubService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.UUID;
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
@RequestMapping(value = "/api/hubs", produces = MediaType.APPLICATION_JSON_VALUE)
public class HubResource {

    private final HubService hubService;

    public HubResource(final HubService hubService) {
        this.hubService = hubService;
    }

    @GetMapping
    public ResponseEntity<List<HubDTO>> getAllHubs() {
        return ResponseEntity.ok(hubService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HubDTO> getHub(@PathVariable final UUID id) {
        return ResponseEntity.ok(hubService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createHub(@RequestBody @Valid final HubDTO hubDTO) {
        return new ResponseEntity<>(hubService.create(hubDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHub(@PathVariable final UUID id,
            @RequestBody @Valid final HubDTO hubDTO) {
        hubService.update(id, hubDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHub(@PathVariable final UUID id) {
        hubService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
