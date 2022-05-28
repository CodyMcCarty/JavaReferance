package io.cody.all_relationships.service;

import io.cody.all_relationships.domain.NeedsBillingAddress;
import io.cody.all_relationships.model.NeedsBillingAddressDTO;
import io.cody.all_relationships.repos.NeedsBillingAddressRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class NeedsBillingAddressService {

    private final NeedsBillingAddressRepository needsBillingAddressRepository;

    public NeedsBillingAddressService(
            final NeedsBillingAddressRepository needsBillingAddressRepository) {
        this.needsBillingAddressRepository = needsBillingAddressRepository;
    }

    public List<NeedsBillingAddressDTO> findAll() {
        return needsBillingAddressRepository.findAll()
                .stream()
                .map(needsBillingAddress -> mapToDTO(needsBillingAddress, new NeedsBillingAddressDTO()))
                .collect(Collectors.toList());
    }

    public NeedsBillingAddressDTO get(final Long id) {
        return needsBillingAddressRepository.findById(id)
                .map(needsBillingAddress -> mapToDTO(needsBillingAddress, new NeedsBillingAddressDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final NeedsBillingAddressDTO needsBillingAddressDTO) {
        final NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
        mapToEntity(needsBillingAddressDTO, needsBillingAddress);
        return needsBillingAddressRepository.save(needsBillingAddress).getId();
    }

    public void update(final Long id, final NeedsBillingAddressDTO needsBillingAddressDTO) {
        final NeedsBillingAddress needsBillingAddress = needsBillingAddressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(needsBillingAddressDTO, needsBillingAddress);
        needsBillingAddressRepository.save(needsBillingAddress);
    }

    public void delete(final Long id) {
        needsBillingAddressRepository.deleteById(id);
    }

    private NeedsBillingAddressDTO mapToDTO(final NeedsBillingAddress needsBillingAddress,
            final NeedsBillingAddressDTO needsBillingAddressDTO) {
        needsBillingAddressDTO.setId(needsBillingAddress.getId());
        needsBillingAddressDTO.setNeedsBillingAddress(needsBillingAddress.getNeedsBillingAddress());
        return needsBillingAddressDTO;
    }

    private NeedsBillingAddress mapToEntity(final NeedsBillingAddressDTO needsBillingAddressDTO,
            final NeedsBillingAddress needsBillingAddress) {
        needsBillingAddress.setNeedsBillingAddress(needsBillingAddressDTO.getNeedsBillingAddress());
        return needsBillingAddress;
    }

}
