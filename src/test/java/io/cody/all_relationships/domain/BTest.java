package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link B}
   *   <li>{@link B#setDateCreated(OffsetDateTime)}
   *   <li>{@link B#setId(Long)}
   *   <li>{@link B#setLastUpdated(OffsetDateTime)}
   *   <li>{@link B#setManyExTwo(String)}
   *   <li>{@link B#setMmFromHubToBHubs(Set)}
   *   <li>{@link B#getDateCreated()}
   *   <li>{@link B#getId()}
   *   <li>{@link B#getLastUpdated()}
   *   <li>{@link B#getManyExTwo()}
   *   <li>{@link B#getMmFromHubToBHubs()}
   * </ul>
   */
  @Test
  void testConstructor() {
    B actualB = new B();
    actualB.setDateCreated(null);
    actualB.setId(123L);
    actualB.setLastUpdated(null);
    actualB.setManyExTwo("Many Ex Two");
    HashSet<Hub> hubSet = new HashSet<>();
    actualB.setMmFromHubToBHubs(hubSet);
    assertNull(actualB.getDateCreated());
    assertEquals(123L, actualB.getId().longValue());
    assertNull(actualB.getLastUpdated());
    assertEquals("Many Ex Two", actualB.getManyExTwo());
    assertSame(hubSet, actualB.getMmFromHubToBHubs());
  }
}

