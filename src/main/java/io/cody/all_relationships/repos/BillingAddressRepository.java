package io.cody.all_relationships.repos;

import io.cody.all_relationships.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
