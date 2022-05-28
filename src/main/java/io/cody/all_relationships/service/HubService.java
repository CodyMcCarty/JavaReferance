package io.cody.all_relationships.service;

import io.cody.all_relationships.domain.B;
import io.cody.all_relationships.domain.Hub;
import io.cody.all_relationships.domain.X;
import io.cody.all_relationships.domain.Y;
import io.cody.all_relationships.model.HubDTO;
import io.cody.all_relationships.repos.BRepository;
import io.cody.all_relationships.repos.HubRepository;
import io.cody.all_relationships.repos.XRepository;
import io.cody.all_relationships.repos.YRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class HubService {

    private final HubRepository hubRepository;
    private final XRepository xRepository;
    private final YRepository yRepository;
    private final BRepository bRepository;

    public HubService(final HubRepository hubRepository, final XRepository xRepository,
            final YRepository yRepository, final BRepository bRepository) {
        this.hubRepository = hubRepository;
        this.xRepository = xRepository;
        this.yRepository = yRepository;
        this.bRepository = bRepository;
    }

    public List<HubDTO> findAll() {
        return hubRepository.findAll()
                .stream()
                .map(hub -> mapToDTO(hub, new HubDTO()))
                .collect(Collectors.toList());
    }

    public HubDTO get(final UUID id) {
        return hubRepository.findById(id)
                .map(hub -> mapToDTO(hub, new HubDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final HubDTO hubDTO) {
        final Hub hub = new Hub();
        mapToEntity(hubDTO, hub);
        return hubRepository.save(hub).getId();
    }

    public void update(final UUID id, final HubDTO hubDTO) {
        final Hub hub = hubRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(hubDTO, hub);
        hubRepository.save(hub);
    }

    public void delete(final UUID id) {
        hubRepository.deleteById(id);
    }

    private HubDTO mapToDTO(final Hub hub, final HubDTO hubDTO) {
        hubDTO.setId(hub.getId());
        hubDTO.setManyExOne(hub.getManyExOne());
        hubDTO.setMoFHubTX(hub.getMoFHubTX() == null ? null : hub.getMoFHubTX().getId());
        hubDTO.setOmFYTHub(hub.getOmFYTHub() == null ? null : hub.getOmFYTHub().getId());
        hubDTO.setMmFromHubToBs(hub.getMmFromHubToBBs() == null ? null : hub.getMmFromHubToBBs().stream()
                .map(b -> b.getId())
                .collect(Collectors.toList()));
        return hubDTO;
    }

    private Hub mapToEntity(final HubDTO hubDTO, final Hub hub) {
        hub.setManyExOne(hubDTO.getManyExOne());
        if (hubDTO.getMoFHubTX() != null && (hub.getMoFHubTX() == null || !hub.getMoFHubTX().getId().equals(hubDTO.getMoFHubTX()))) {
            final X moFHubTX = xRepository.findById(hubDTO.getMoFHubTX())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "moFHubTX not found"));
            hub.setMoFHubTX(moFHubTX);
        }
        if (hubDTO.getOmFYTHub() != null && (hub.getOmFYTHub() == null || !hub.getOmFYTHub().getId().equals(hubDTO.getOmFYTHub()))) {
            final Y omFYTHub = yRepository.findById(hubDTO.getOmFYTHub())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "omFYTHub not found"));
            hub.setOmFYTHub(omFYTHub);
        }
        if (hubDTO.getMmFromHubToBs() != null) {
            final List<B> mmFromHubToBs = bRepository.findAllById(hubDTO.getMmFromHubToBs());
            if (mmFromHubToBs.size() != hubDTO.getMmFromHubToBs().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of mmFromHubToBs not found");
            }
            hub.setMmFromHubToBBs(mmFromHubToBs.stream().collect(Collectors.toSet()));
        }
        return hub;
    }

}
