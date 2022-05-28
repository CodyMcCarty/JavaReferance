package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class YDTOTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link YDTO}
   *   <li>{@link YDTO#setHasOneToManyRel(String)}
   *   <li>{@link YDTO#setId(Long)}
   *   <li>{@link YDTO#getHasOneToManyRel()}
   *   <li>{@link YDTO#getId()}
   * </ul>
   */
  @Test
  void testConstructor() {
    YDTO actualYdto = new YDTO();
    actualYdto.setHasOneToManyRel("Has One To Many Rel");
    actualYdto.setId(123L);
    assertEquals("Has One To Many Rel", actualYdto.getHasOneToManyRel());
    assertEquals(123L, actualYdto.getId().longValue());
  }
}

