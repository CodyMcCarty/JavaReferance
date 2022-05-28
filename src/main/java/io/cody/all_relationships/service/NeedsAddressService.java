package io.cody.all_relationships.service;

import io.cody.all_relationships.domain.NeedsAddress;
import io.cody.all_relationships.model.NeedsAddressDTO;
import io.cody.all_relationships.repos.NeedsAddressRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class NeedsAddressService {

    private final NeedsAddressRepository needsAddressRepository;

    public NeedsAddressService(final NeedsAddressRepository needsAddressRepository) {
        this.needsAddressRepository = needsAddressRepository;
    }

    public List<NeedsAddressDTO> findAll() {
        return needsAddressRepository.findAll()
                .stream()
                .map(needsAddress -> mapToDTO(needsAddress, new NeedsAddressDTO()))
                .collect(Collectors.toList());
    }

    public NeedsAddressDTO get(final Long id) {
        return needsAddressRepository.findById(id)
                .map(needsAddress -> mapToDTO(needsAddress, new NeedsAddressDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final NeedsAddressDTO needsAddressDTO) {
        final NeedsAddress needsAddress = new NeedsAddress();
        mapToEntity(needsAddressDTO, needsAddress);
        return needsAddressRepository.save(needsAddress).getId();
    }

    public void update(final Long id, final NeedsAddressDTO needsAddressDTO) {
        final NeedsAddress needsAddress = needsAddressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(needsAddressDTO, needsAddress);
        needsAddressRepository.save(needsAddress);
    }

    public void delete(final Long id) {
        needsAddressRepository.deleteById(id);
    }

    private NeedsAddressDTO mapToDTO(final NeedsAddress needsAddress,
            final NeedsAddressDTO needsAddressDTO) {
        needsAddressDTO.setId(needsAddress.getId());
        needsAddressDTO.setNeedsAddress(needsAddress.getNeedsAddress());
        return needsAddressDTO;
    }

    private NeedsAddress mapToEntity(final NeedsAddressDTO needsAddressDTO,
            final NeedsAddress needsAddress) {
        needsAddress.setNeedsAddress(needsAddressDTO.getNeedsAddress());
        return needsAddress;
    }

}
