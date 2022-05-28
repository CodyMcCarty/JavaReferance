package io.cody.all_relationships.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class HubDTOTest {

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link HubDTO}
   *   <li>{@link HubDTO#setId(UUID)}
   *   <li>{@link HubDTO#setManyExOne(String)}
   *   <li>{@link HubDTO#setMmFromHubToBs(List)}
   *   <li>{@link HubDTO#setMoFHubTX(Long)}
   *   <li>{@link HubDTO#setOmFYTHub(Long)}
   *   <li>{@link HubDTO#getId()}
   *   <li>{@link HubDTO#getManyExOne()}
   *   <li>{@link HubDTO#getMmFromHubToBs()}
   *   <li>{@link HubDTO#getMoFHubTX()}
   *   <li>{@link HubDTO#getOmFYTHub()}
   * </ul>
   */
  @Test
  void testConstructor() {
    HubDTO actualHubDTO = new HubDTO();
    UUID randomUUIDResult = UUID.randomUUID();
    actualHubDTO.setId(randomUUIDResult);
    actualHubDTO.setManyExOne("Many Ex One");
    ArrayList<Long> resultLongList = new ArrayList<>();
    actualHubDTO.setMmFromHubToBs(resultLongList);
    actualHubDTO.setMoFHubTX(1L);
    actualHubDTO.setOmFYTHub(1L);
    assertSame(randomUUIDResult, actualHubDTO.getId());
    assertEquals("Many Ex One", actualHubDTO.getManyExOne());
    assertSame(resultLongList, actualHubDTO.getMmFromHubToBs());
    assertEquals(1L, actualHubDTO.getMoFHubTX().longValue());
    assertEquals(1L, actualHubDTO.getOmFYTHub().longValue());
  }
}

