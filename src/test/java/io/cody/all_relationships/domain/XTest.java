package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import io.cody.all_relationships.model.Clearnance;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class XTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link X}
   *   <li>{@link X#setClearance(Clearnance)}
   *   <li>{@link X#setDateCreated(OffsetDateTime)}
   *   <li>{@link X#setHasManyToOneRel(String)}
   *   <li>{@link X#setHasOneToOne(String)}
   *   <li>{@link X#setId(Long)}
   *   <li>{@link X#setLastUpdated(OffsetDateTime)}
   *   <li>{@link X#setMoFHubTXHubs(Set)}
   *   <li>{@link X#setOoFXTZ(Z)}
   *   <li>{@link X#getClearance()}
   *   <li>{@link X#getOoFXTZ()}
   *   <li>{@link X#getDateCreated()}
   *   <li>{@link X#getHasManyToOneRel()}
   *   <li>{@link X#getHasOneToOne()}
   *   <li>{@link X#getId()}
   *   <li>{@link X#getLastUpdated()}
   *   <li>{@link X#getMoFHubTXHubs()}
   * </ul>
   */
  @Test
  void testConstructor() {
    X actualX = new X();
    actualX.setClearance(Clearnance.VIEWER);
    actualX.setDateCreated(null);
    actualX.setHasManyToOneRel("Has Many To One Rel");
    actualX.setHasOneToOne("Has One To One");
    actualX.setId(123L);
    actualX.setLastUpdated(null);
    HashSet<Hub> hubSet = new HashSet<>();
    actualX.setMoFHubTXHubs(hubSet);
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    HashSet<Hub> hubSet1 = new HashSet<>();
    x.setMoFHubTXHubs(hubSet1);
    Z z = new Z();
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
    HashSet<Hub> hubSet2 = new HashSet<>();
    x1.setMoFHubTXHubs(hubSet2);
    x1.setOoFXTZ(z1);
    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x1);
    actualX.setOoFXTZ(z2);
    assertEquals(Clearnance.VIEWER, actualX.getClearance());
    Z ooFXTZ = actualX.getOoFXTZ();
    X ooFXTZ1 = ooFXTZ.getOoFXTZ();
    assertEquals(Clearnance.VIEWER, ooFXTZ1.getClearance());
    Z ooFXTZ2 = ooFXTZ1.getOoFXTZ();
    X ooFXTZ3 = ooFXTZ2.getOoFXTZ();
    assertEquals(Clearnance.VIEWER, ooFXTZ3.getClearance());
    assertNull(actualX.getDateCreated());
    assertNull(ooFXTZ1.getDateCreated());
    assertNull(ooFXTZ3.getDateCreated());
    assertEquals("Has Many To One Rel", actualX.getHasManyToOneRel());
    assertEquals("Has Many To One Rel", ooFXTZ1.getHasManyToOneRel());
    assertEquals("Has Many To One Rel", ooFXTZ3.getHasManyToOneRel());
    assertEquals("Has One To One", actualX.getHasOneToOne());
    assertEquals("Has One To One", ooFXTZ1.getHasOneToOne());
    assertEquals("Has One To One", ooFXTZ3.getHasOneToOne());
    assertEquals(123L, actualX.getId().longValue());
    assertEquals(123L, ooFXTZ1.getId().longValue());
    assertEquals(123L, ooFXTZ3.getId().longValue());
    assertNull(actualX.getLastUpdated());
    assertNull(ooFXTZ1.getLastUpdated());
    assertNull(ooFXTZ3.getLastUpdated());
    assertSame(hubSet, actualX.getMoFHubTXHubs());
    assertSame(hubSet2, ooFXTZ1.getMoFHubTXHubs());
    assertSame(hubSet1, ooFXTZ3.getMoFHubTXHubs());
    assertSame(z2, ooFXTZ);
    assertSame(z1, ooFXTZ2);
    assertSame(z, ooFXTZ3.getOoFXTZ());
  }
}

