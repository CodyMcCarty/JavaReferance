package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NeedsBillingAddressDTOTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link NeedsBillingAddressDTO}
   *   <li>{@link NeedsBillingAddressDTO#setId(Long)}
   *   <li>{@link NeedsBillingAddressDTO#setNeedsBillingAddress(String)}
   *   <li>{@link NeedsBillingAddressDTO#getId()}
   *   <li>{@link NeedsBillingAddressDTO#getNeedsBillingAddress()}
   * </ul>
   */
  @Test
  void testConstructor() {
    NeedsBillingAddressDTO actualNeedsBillingAddressDTO = new NeedsBillingAddressDTO();
    actualNeedsBillingAddressDTO.setId(123L);
    actualNeedsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    assertEquals(123L, actualNeedsBillingAddressDTO.getId().longValue());
    assertEquals("42 Main St", actualNeedsBillingAddressDTO.getNeedsBillingAddress());
  }
}

