package io.cody.all_relationships.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.domain.Y;
import io.cody.all_relationships.model.YDTO;
import io.cody.all_relationships.repos.YRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {YService.class})
@ExtendWith(SpringExtension.class)
class YServiceTest {

  @MockBean
  private YRepository yRepository;

  @Autowired
  private YService yService;

  /**
   * Method under test: {@link YService#findAll()}
   */
  @Test
  void testFindAll() {
    when(this.yRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.yService.findAll().isEmpty());
    verify(this.yRepository).findAll();
  }

  /**
   * Method under test: {@link YService#findAll()}
   */
  @Test
  void testFindAll2() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    ArrayList<Y> yList = new ArrayList<>();
    yList.add(y);
    when(this.yRepository.findAll()).thenReturn(yList);
    List<YDTO> actualFindAllResult = this.yService.findAll();
    assertEquals(1, actualFindAllResult.size());
    YDTO getResult = actualFindAllResult.get(0);
    assertEquals("Has One To Many Rel", getResult.getHasOneToManyRel());
    assertEquals(123L, getResult.getId().longValue());
    verify(this.yRepository).findAll();
  }

  /**
   * Method under test: {@link YService#findAll()}
   */
  @Test
  void testFindAll3() {
    when(this.yRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.yService.findAll());
    verify(this.yRepository).findAll();
  }

  /**
   * Method under test: {@link YService#findAll()}
   */
  @Test
  void testFindAll4() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    ArrayList<Y> yList = new ArrayList<>();
    yList.add(y1);
    yList.add(y);
    when(this.yRepository.findAll()).thenReturn(yList);
    List<YDTO> actualFindAllResult = this.yService.findAll();
    assertEquals(2, actualFindAllResult.size());
    YDTO getResult = actualFindAllResult.get(0);
    assertEquals(123L, getResult.getId().longValue());
    YDTO getResult1 = actualFindAllResult.get(1);
    assertEquals(123L, getResult1.getId().longValue());
    assertEquals("Has One To Many Rel", getResult1.getHasOneToManyRel());
    assertEquals("Has One To Many Rel", getResult.getHasOneToManyRel());
    verify(this.yRepository).findAll();
  }

  /**
   * Method under test: {@link YService#get(Long)}
   */
  @Test
  void testGet() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);
    YDTO actualGetResult = this.yService.get(123L);
    assertEquals("Has One To Many Rel", actualGetResult.getHasOneToManyRel());
    assertEquals(123L, actualGetResult.getId().longValue());
    verify(this.yRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link YService#get(Long)}
   */
  @Test
  void testGet2() {
    when(this.yRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> this.yService.get(123L));
    verify(this.yRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link YService#get(Long)}
   */
  @Test
  void testGet3() {
    when(this.yRepository.findById((Long) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.yService.get(123L));
    verify(this.yRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link YService#create(YDTO)}
   */
  @Test
  void testCreate() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    when(this.yRepository.save((Y) any())).thenReturn(y);

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    assertEquals(123L, this.yService.create(ydto).longValue());
    verify(this.yRepository).save((Y) any());
  }

  /**
   * Method under test: {@link YService#create(YDTO)}
   */
  @Test
  void testCreate2() {
    when(this.yRepository.save((Y) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    assertThrows(ResponseStatusException.class, () -> this.yService.create(ydto));
    verify(this.yRepository).save((Y) any());
  }

  /**
   * Method under test: {@link YService#update(Long, YDTO)}
   */
  @Test
  void testUpdate() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());
    when(this.yRepository.save((Y) any())).thenReturn(y1);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    this.yService.update(123L, ydto);
    verify(this.yRepository).save((Y) any());
    verify(this.yRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link YService#update(Long, YDTO)}
   */
  @Test
  void testUpdate2() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.save((Y) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    assertThrows(ResponseStatusException.class, () -> this.yService.update(123L, ydto));
    verify(this.yRepository).save((Y) any());
    verify(this.yRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link YService#update(Long, YDTO)}
   */
  @Test
  void testUpdate3() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    when(this.yRepository.save((Y) any())).thenReturn(y);
    when(this.yRepository.findById((Long) any())).thenReturn(Optional.empty());

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    assertThrows(ResponseStatusException.class, () -> this.yService.update(123L, ydto));
    verify(this.yRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link YService#delete(Long)}
   */
  @Test
  void testDelete() {
    doNothing().when(this.yRepository).deleteById((Long) any());
    this.yService.delete(123L);
    verify(this.yRepository).deleteById((Long) any());
  }

  /**
   * Method under test: {@link YService#delete(Long)}
   */
  @Test
  void testDelete2() {
    doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(this.yRepository)
        .deleteById((Long) any());
    assertThrows(ResponseStatusException.class, () -> this.yService.delete(123L));
    verify(this.yRepository).deleteById((Long) any());
  }
}

