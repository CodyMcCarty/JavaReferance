package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class YTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Y}
   *   <li>{@link Y#setDateCreated(OffsetDateTime)}
   *   <li>{@link Y#setHasOneToManyRel(String)}
   *   <li>{@link Y#setId(Long)}
   *   <li>{@link Y#setLastUpdated(OffsetDateTime)}
   *   <li>{@link Y#setOmFYTHubHubs(Set)}
   *   <li>{@link Y#getDateCreated()}
   *   <li>{@link Y#getHasOneToManyRel()}
   *   <li>{@link Y#getId()}
   *   <li>{@link Y#getLastUpdated()}
   *   <li>{@link Y#getOmFYTHubHubs()}
   * </ul>
   */
  @Test
  void testConstructor() {
    Y actualY = new Y();
    actualY.setDateCreated(null);
    actualY.setHasOneToManyRel("Has One To Many Rel");
    actualY.setId(123L);
    actualY.setLastUpdated(null);
    HashSet<Hub> hubSet = new HashSet<>();
    actualY.setOmFYTHubHubs(hubSet);
    assertNull(actualY.getDateCreated());
    assertEquals("Has One To Many Rel", actualY.getHasOneToManyRel());
    assertEquals(123L, actualY.getId().longValue());
    assertNull(actualY.getLastUpdated());
    assertSame(hubSet, actualY.getOmFYTHubHubs());
  }
}

