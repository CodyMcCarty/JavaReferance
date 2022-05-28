package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class NeedsAddressTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link NeedsAddress}
   *   <li>{@link NeedsAddress#setDateCreated(OffsetDateTime)}
   *   <li>{@link NeedsAddress#setId(Long)}
   *   <li>{@link NeedsAddress#setLastUpdated(OffsetDateTime)}
   *   <li>{@link NeedsAddress#setMoFAddTNeedsAddAddresss(Set)}
   *   <li>{@link NeedsAddress#setNeedsAddress(String)}
   *   <li>{@link NeedsAddress#getDateCreated()}
   *   <li>{@link NeedsAddress#getId()}
   *   <li>{@link NeedsAddress#getLastUpdated()}
   *   <li>{@link NeedsAddress#getMoFAddTNeedsAddAddresss()}
   *   <li>{@link NeedsAddress#getNeedsAddress()}
   * </ul>
   */
  @Test
  void testConstructor() {
    NeedsAddress actualNeedsAddress = new NeedsAddress();
    actualNeedsAddress.setDateCreated(null);
    actualNeedsAddress.setId(123L);
    actualNeedsAddress.setLastUpdated(null);
    HashSet<Address> addressSet = new HashSet<>();
    actualNeedsAddress.setMoFAddTNeedsAddAddresss(addressSet);
    actualNeedsAddress.setNeedsAddress("42 Main St");
    assertNull(actualNeedsAddress.getDateCreated());
    assertEquals(123L, actualNeedsAddress.getId().longValue());
    assertNull(actualNeedsAddress.getLastUpdated());
    assertSame(addressSet, actualNeedsAddress.getMoFAddTNeedsAddAddresss());
    assertEquals("42 Main St", actualNeedsAddress.getNeedsAddress());
  }
}

