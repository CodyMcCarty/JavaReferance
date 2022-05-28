package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NeedsAddressDTOTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link NeedsAddressDTO}
   *   <li>{@link NeedsAddressDTO#setId(Long)}
   *   <li>{@link NeedsAddressDTO#setNeedsAddress(String)}
   *   <li>{@link NeedsAddressDTO#getId()}
   *   <li>{@link NeedsAddressDTO#getNeedsAddress()}
   * </ul>
   */
  @Test
  void testConstructor() {
    NeedsAddressDTO actualNeedsAddressDTO = new NeedsAddressDTO();
    actualNeedsAddressDTO.setId(123L);
    actualNeedsAddressDTO.setNeedsAddress("42 Main St");
    assertEquals(123L, actualNeedsAddressDTO.getId().longValue());
    assertEquals("42 Main St", actualNeedsAddressDTO.getNeedsAddress());
  }
}

