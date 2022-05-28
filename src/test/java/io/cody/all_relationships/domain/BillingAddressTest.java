package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.OffsetDateTime;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class BillingAddressTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BillingAddress}
   *   <li>{@link BillingAddress#setDateCreated(OffsetDateTime)}
   *   <li>{@link BillingAddress#setEmail(String)}
   *   <li>{@link BillingAddress#setId(Long)}
   *   <li>{@link BillingAddress#setLastUpdated(OffsetDateTime)}
   *   <li>{@link BillingAddress#setOmFNeedsBillAddTBillAdd(NeedsBillingAddress)}
   *   <li>{@link BillingAddress#setPhoneNumber(String)}
   *   <li>{@link BillingAddress#getDateCreated()}
   *   <li>{@link BillingAddress#getEmail()}
   *   <li>{@link BillingAddress#getId()}
   *   <li>{@link BillingAddress#getLastUpdated()}
   *   <li>{@link BillingAddress#getOmFNeedsBillAddTBillAdd()}
   *   <li>{@link BillingAddress#getPhoneNumber()}
   * </ul>
   */
  @Test
  void testConstructor() {
    BillingAddress actualBillingAddress = new BillingAddress();
    actualBillingAddress.setDateCreated(null);
    actualBillingAddress.setEmail("jane.doe@example.org");
    actualBillingAddress.setId(123L);
    actualBillingAddress.setLastUpdated(null);
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    actualBillingAddress.setOmFNeedsBillAddTBillAdd(needsBillingAddress);
    actualBillingAddress.setPhoneNumber("4105551212");
    assertNull(actualBillingAddress.getDateCreated());
    assertEquals("jane.doe@example.org", actualBillingAddress.getEmail());
    assertEquals(123L, actualBillingAddress.getId().longValue());
    assertNull(actualBillingAddress.getLastUpdated());
    assertSame(needsBillingAddress, actualBillingAddress.getOmFNeedsBillAddTBillAdd());
    assertEquals("4105551212", actualBillingAddress.getPhoneNumber());
  }
}

