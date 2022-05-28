package io.cody.all_relationships.service;

import io.cody.all_relationships.domain.B;
import io.cody.all_relationships.model.BDTO;
import io.cody.all_relationships.repos.BRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class BService {

    private final BRepository bRepository;

    public BService(final BRepository bRepository) {
        this.bRepository = bRepository;
    }

    public List<BDTO> findAll() {
        return bRepository.findAll()
                .stream()
                .map(b -> mapToDTO(b, new BDTO()))
                .collect(Collectors.toList());
    }

    public BDTO get(final Long id) {
        return bRepository.findById(id)
                .map(b -> mapToDTO(b, new BDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final BDTO bDTO) {
        final B b = new B();
        mapToEntity(bDTO, b);
        return bRepository.save(b).getId();
    }

    public void update(final Long id, final BDTO bDTO) {
        final B b = bRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(bDTO, b);
        bRepository.save(b);
    }

    public void delete(final Long id) {
        bRepository.deleteById(id);
    }

    private BDTO mapToDTO(final B b, final BDTO bDTO) {
        bDTO.setId(b.getId());
        bDTO.setManyExTwo(b.getManyExTwo());
        return bDTO;
    }

    private B mapToEntity(final BDTO bDTO, final B b) {
        b.setManyExTwo(bDTO.getManyExTwo());
        return b;
    }

}
