package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.OffsetDateTime;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class AddressTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Address}
   *   <li>{@link Address#setCity(String)}
   *   <li>{@link Address#setDateCreated(OffsetDateTime)}
   *   <li>{@link Address#setId(Long)}
   *   <li>{@link Address#setLastUpdated(OffsetDateTime)}
   *   <li>{@link Address#setMoFAddTNeedsAdd(NeedsAddress)}
   *   <li>{@link Address#setState(String)}
   *   <li>{@link Address#setStreet2(String)}
   *   <li>{@link Address#setStreet(String)}
   *   <li>{@link Address#setZip(Integer)}
   *   <li>{@link Address#getCity()}
   *   <li>{@link Address#getDateCreated()}
   *   <li>{@link Address#getId()}
   *   <li>{@link Address#getLastUpdated()}
   *   <li>{@link Address#getMoFAddTNeedsAdd()}
   *   <li>{@link Address#getState()}
   *   <li>{@link Address#getStreet2()}
   *   <li>{@link Address#getStreet()}
   *   <li>{@link Address#getZip()}
   * </ul>
   */
  @Test
  void testConstructor() {
    Address actualAddress = new Address();
    actualAddress.setCity("Oxford");
    actualAddress.setDateCreated(null);
    actualAddress.setId(123L);
    actualAddress.setLastUpdated(null);
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");
    actualAddress.setMoFAddTNeedsAdd(needsAddress);
    actualAddress.setState("MD");
    actualAddress.setStreet2("Street2");
    actualAddress.setStreet("Street");
    actualAddress.setZip(1);
    assertEquals("Oxford", actualAddress.getCity());
    assertNull(actualAddress.getDateCreated());
    assertEquals(123L, actualAddress.getId().longValue());
    assertNull(actualAddress.getLastUpdated());
    assertSame(needsAddress, actualAddress.getMoFAddTNeedsAdd());
    assertEquals("MD", actualAddress.getState());
    assertEquals("Street2", actualAddress.getStreet2());
    assertEquals("Street", actualAddress.getStreet());
    assertEquals(1, actualAddress.getZip().intValue());
  }
}

