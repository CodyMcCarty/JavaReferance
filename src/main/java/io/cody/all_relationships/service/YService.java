package io.cody.all_relationships.service;

import io.cody.all_relationships.domain.Y;
import io.cody.all_relationships.model.YDTO;
import io.cody.all_relationships.repos.YRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class YService {

    private final YRepository yRepository;

    public YService(final YRepository yRepository) {
        this.yRepository = yRepository;
    }

    public List<YDTO> findAll() {
        return yRepository.findAll()
                .stream()
                .map(y -> mapToDTO(y, new YDTO()))
                .collect(Collectors.toList());
    }

    public YDTO get(final Long id) {
        return yRepository.findById(id)
                .map(y -> mapToDTO(y, new YDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final YDTO yDTO) {
        final Y y = new Y();
        mapToEntity(yDTO, y);
        return yRepository.save(y).getId();
    }

    public void update(final Long id, final YDTO yDTO) {
        final Y y = yRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(yDTO, y);
        yRepository.save(y);
    }

    public void delete(final Long id) {
        yRepository.deleteById(id);
    }

    private YDTO mapToDTO(final Y y, final YDTO yDTO) {
        yDTO.setId(y.getId());
        yDTO.setHasOneToManyRel(y.getHasOneToManyRel());
        return yDTO;
    }

    private Y mapToEntity(final YDTO yDTO, final Y y) {
        y.setHasOneToManyRel(yDTO.getHasOneToManyRel());
        return y;
    }

}
