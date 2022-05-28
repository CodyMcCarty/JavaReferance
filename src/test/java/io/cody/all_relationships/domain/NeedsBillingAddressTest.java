package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class NeedsBillingAddressTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link NeedsBillingAddress}
   *   <li>{@link NeedsBillingAddress#setDateCreated(OffsetDateTime)}
   *   <li>{@link NeedsBillingAddress#setId(Long)}
   *   <li>{@link NeedsBillingAddress#setLastUpdated(OffsetDateTime)}
   *   <li>{@link NeedsBillingAddress#setNeedsBillingAddress(String)}
   *   <li>{@link NeedsBillingAddress#setOmFNeedsBillAddTBillAddBillingAddresss(Set)}
   *   <li>{@link NeedsBillingAddress#getDateCreated()}
   *   <li>{@link NeedsBillingAddress#getId()}
   *   <li>{@link NeedsBillingAddress#getLastUpdated()}
   *   <li>{@link NeedsBillingAddress#getNeedsBillingAddress()}
   *   <li>{@link NeedsBillingAddress#getOmFNeedsBillAddTBillAddBillingAddresss()}
   * </ul>
   */
  @Test
  void testConstructor() {
    NeedsBillingAddress actualNeedsBillingAddress = new NeedsBillingAddress();
    actualNeedsBillingAddress.setDateCreated(null);
    actualNeedsBillingAddress.setId(123L);
    actualNeedsBillingAddress.setLastUpdated(null);
    actualNeedsBillingAddress.setNeedsBillingAddress("42 Main St");
    HashSet<BillingAddress> billingAddressSet = new HashSet<>();
    actualNeedsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(billingAddressSet);
    assertNull(actualNeedsBillingAddress.getDateCreated());
    assertEquals(123L, actualNeedsBillingAddress.getId().longValue());
    assertNull(actualNeedsBillingAddress.getLastUpdated());
    assertEquals("42 Main St", actualNeedsBillingAddress.getNeedsBillingAddress());
    assertSame(billingAddressSet,
        actualNeedsBillingAddress.getOmFNeedsBillAddTBillAddBillingAddresss());
  }
}

