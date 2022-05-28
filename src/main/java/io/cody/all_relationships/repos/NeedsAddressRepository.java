package io.cody.all_relationships.repos;

import io.cody.all_relationships.domain.NeedsAddress;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NeedsAddressRepository extends JpaRepository<NeedsAddress, Long> {
}
