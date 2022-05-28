package io.cody.all_relationships.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import io.cody.all_relationships.model.Clearnance;
import java.time.OffsetDateTime;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class ZTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Z}
   *   <li>{@link Z#setDateCreated(OffsetDateTime)}
   *   <li>{@link Z#setHasOneToOne(String)}
   *   <li>{@link Z#setId(Long)}
   *   <li>{@link Z#setLastUpdated(OffsetDateTime)}
   *   <li>{@link Z#setOoFXTZ(X)}
   *   <li>{@link Z#getDateCreated()}
   *   <li>{@link Z#getOoFXTZ()}
   *   <li>{@link Z#getHasOneToOne()}
   *   <li>{@link Z#getId()}
   *   <li>{@link Z#getLastUpdated()}
   * </ul>
   */
  @Test
  void testConstructor() {
    Z actualZ = new Z();
    actualZ.setDateCreated(null);
    actualZ.setHasOneToOne("Has One To One");
    actualZ.setId(123L);
    actualZ.setLastUpdated(null);
    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    X x = new X();
    z.setOoFXTZ(x);
    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z);
    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);
    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z1);
    actualZ.setOoFXTZ(x2);
    assertNull(actualZ.getDateCreated());
    X ooFXTZ = actualZ.getOoFXTZ();
    Z ooFXTZ1 = ooFXTZ.getOoFXTZ();
    assertNull(ooFXTZ1.getDateCreated());
    X ooFXTZ2 = ooFXTZ1.getOoFXTZ();
    Z ooFXTZ3 = ooFXTZ2.getOoFXTZ();
    assertNull(ooFXTZ3.getDateCreated());
    assertEquals("Has One To One", actualZ.getHasOneToOne());
    assertEquals("Has One To One", ooFXTZ1.getHasOneToOne());
    assertEquals("Has One To One", ooFXTZ3.getHasOneToOne());
    assertEquals(123L, actualZ.getId().longValue());
    assertEquals(123L, ooFXTZ1.getId().longValue());
    assertEquals(123L, ooFXTZ3.getId().longValue());
    assertNull(actualZ.getLastUpdated());
    assertNull(ooFXTZ1.getLastUpdated());
    assertNull(ooFXTZ3.getLastUpdated());
    assertSame(x2, ooFXTZ);
    assertSame(x1, ooFXTZ2);
    assertSame(x, ooFXTZ3.getOoFXTZ());
  }
}

