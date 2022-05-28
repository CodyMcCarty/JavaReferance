package io.cody.all_relationships.repos;

import io.cody.all_relationships.domain.Hub;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HubRepository extends JpaRepository<Hub, UUID> {
}
