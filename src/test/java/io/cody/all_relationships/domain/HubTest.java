package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import io.cody.all_relationships.model.Clearnance;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class HubTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Hub}
   *   <li>{@link Hub#setDateCreated(OffsetDateTime)}
   *   <li>{@link Hub#setId(UUID)}
   *   <li>{@link Hub#setLastUpdated(OffsetDateTime)}
   *   <li>{@link Hub#setManyExOne(String)}
   *   <li>{@link Hub#setMmFromHubToBBs(Set)}
   *   <li>{@link Hub#setMoFHubTX(X)}
   *   <li>{@link Hub#setOmFYTHub(Y)}
   *   <li>{@link Hub#getDateCreated()}
   *   <li>{@link Hub#getId()}
   *   <li>{@link Hub#getLastUpdated()}
   *   <li>{@link Hub#getManyExOne()}
   *   <li>{@link Hub#getMmFromHubToBBs()}
   *   <li>{@link Hub#getMoFHubTX()}
   *   <li>{@link Hub#getOmFYTHub()}
   * </ul>
   */
  @Test
  void testConstructor() {
    Hub actualHub = new Hub();
    actualHub.setDateCreated(null);
    UUID randomUUIDResult = UUID.randomUUID();
    actualHub.setId(randomUUIDResult);
    actualHub.setLastUpdated(null);
    actualHub.setManyExOne("Many Ex One");
    HashSet<B> bSet = new HashSet<>();
    actualHub.setMmFromHubToBBs(bSet);
    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);
    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);
    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    actualHub.setMoFHubTX(x1);
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    actualHub.setOmFYTHub(y);
    assertNull(actualHub.getDateCreated());
    assertSame(randomUUIDResult, actualHub.getId());
    assertNull(actualHub.getLastUpdated());
    assertEquals("Many Ex One", actualHub.getManyExOne());
    assertSame(bSet, actualHub.getMmFromHubToBBs());
    assertSame(x1, actualHub.getMoFHubTX());
    assertSame(y, actualHub.getOmFYTHub());
  }
}

