package io.cody.all_relationships.service;

import io.cody.all_relationships.domain.X;
import io.cody.all_relationships.domain.Z;
import io.cody.all_relationships.model.XDTO;
import io.cody.all_relationships.repos.XRepository;
import io.cody.all_relationships.repos.ZRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class XService {

    private final XRepository xRepository;
    private final ZRepository zRepository;

    public XService(final XRepository xRepository, final ZRepository zRepository) {
        this.xRepository = xRepository;
        this.zRepository = zRepository;
    }

    public List<XDTO> findAll() {
        return xRepository.findAll()
                .stream()
                .map(x -> mapToDTO(x, new XDTO()))
                .collect(Collectors.toList());
    }

    public XDTO get(final Long id) {
        return xRepository.findById(id)
                .map(x -> mapToDTO(x, new XDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final XDTO xDTO) {
        final X x = new X();
        mapToEntity(xDTO, x);
        return xRepository.save(x).getId();
    }

    public void update(final Long id, final XDTO xDTO) {
        final X x = xRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(xDTO, x);
        xRepository.save(x);
    }

    public void delete(final Long id) {
        xRepository.deleteById(id);
    }

    private XDTO mapToDTO(final X x, final XDTO xDTO) {
        xDTO.setId(x.getId());
        xDTO.setHasManyToOneRel(x.getHasManyToOneRel());
        xDTO.setHasOneToOne(x.getHasOneToOne());
        xDTO.setClearance(x.getClearance());
        xDTO.setOoFXTZ(x.getOoFXTZ() == null ? null : x.getOoFXTZ().getId());
        return xDTO;
    }

    private X mapToEntity(final XDTO xDTO, final X x) {
        x.setHasManyToOneRel(xDTO.getHasManyToOneRel());
        x.setHasOneToOne(xDTO.getHasOneToOne());
        x.setClearance(xDTO.getClearance());
        if (xDTO.getOoFXTZ() != null && (x.getOoFXTZ() == null || !x.getOoFXTZ().getId().equals(xDTO.getOoFXTZ()))) {
            final Z ooFXTZ = zRepository.findById(xDTO.getOoFXTZ())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ooFXTZ not found"));
            x.setOoFXTZ(ooFXTZ);
        }
        return x;
    }

}
