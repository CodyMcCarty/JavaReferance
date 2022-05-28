package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class XDTOTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link XDTO}
   *   <li>{@link XDTO#setClearance(Clearnance)}
   *   <li>{@link XDTO#setHasManyToOneRel(String)}
   *   <li>{@link XDTO#setHasOneToOne(String)}
   *   <li>{@link XDTO#setId(Long)}
   *   <li>{@link XDTO#setOoFXTZ(Long)}
   *   <li>{@link XDTO#getClearance()}
   *   <li>{@link XDTO#getHasManyToOneRel()}
   *   <li>{@link XDTO#getHasOneToOne()}
   *   <li>{@link XDTO#getId()}
   *   <li>{@link XDTO#getOoFXTZ()}
   * </ul>
   */
  @Test
  void testConstructor() {
    XDTO actualXdto = new XDTO();
    actualXdto.setClearance(Clearnance.VIEWER);
    actualXdto.setHasManyToOneRel("Has Many To One Rel");
    actualXdto.setHasOneToOne("Has One To One");
    actualXdto.setId(123L);
    actualXdto.setOoFXTZ(1L);
    assertEquals(Clearnance.VIEWER, actualXdto.getClearance());
    assertEquals("Has Many To One Rel", actualXdto.getHasManyToOneRel());
    assertEquals("Has One To One", actualXdto.getHasOneToOne());
    assertEquals(123L, actualXdto.getId().longValue());
    assertEquals(1L, actualXdto.getOoFXTZ().longValue());
  }
}

