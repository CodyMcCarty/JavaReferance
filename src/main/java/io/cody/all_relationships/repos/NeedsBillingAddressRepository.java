package io.cody.all_relationships.repos;

import io.cody.all_relationships.domain.NeedsBillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NeedsBillingAddressRepository extends JpaRepository<NeedsBillingAddress, Long> {
}
