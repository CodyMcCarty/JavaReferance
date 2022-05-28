package io.cody.all_relationships.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.domain.B;
import io.cody.all_relationships.model.BDTO;
import io.cody.all_relationships.repos.BRepository;
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

@ContextConfiguration(classes = {BService.class})
@ExtendWith(SpringExtension.class)
class BServiceTest {

  @MockBean
  private BRepository bRepository;

  @Autowired
  private BService bService;

  /**
   * Method under test: {@link BService#findAll()}
   */
  @Test
  void testFindAll() {
    when(this.bRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.bService.findAll().isEmpty());
    verify(this.bRepository).findAll();
  }

  /**
   * Method under test: {@link BService#findAll()}
   */
  @Test
  void testFindAll2() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());

    ArrayList<B> bList = new ArrayList<>();
    bList.add(b);
    when(this.bRepository.findAll()).thenReturn(bList);
    List<BDTO> actualFindAllResult = this.bService.findAll();
    assertEquals(1, actualFindAllResult.size());
    BDTO getResult = actualFindAllResult.get(0);
    assertEquals(123L, getResult.getId().longValue());
    assertEquals("Many Ex Two", getResult.getManyExTwo());
    verify(this.bRepository).findAll();
  }

  /**
   * Method under test: {@link BService#findAll()}
   */
  @Test
  void testFindAll3() {
    when(this.bRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.bService.findAll());
    verify(this.bRepository).findAll();
  }

  /**
   * Method under test: {@link BService#findAll()}
   */
  @Test
  void testFindAll4() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());

    B b1 = new B();
    b1.setDateCreated(null);
    b1.setId(123L);
    b1.setLastUpdated(null);
    b1.setManyExTwo("Many Ex Two");
    b1.setMmFromHubToBHubs(new HashSet<>());

    ArrayList<B> bList = new ArrayList<>();
    bList.add(b1);
    bList.add(b);
    when(this.bRepository.findAll()).thenReturn(bList);
    List<BDTO> actualFindAllResult = this.bService.findAll();
    assertEquals(2, actualFindAllResult.size());
    BDTO getResult = actualFindAllResult.get(0);
    assertEquals("Many Ex Two", getResult.getManyExTwo());
    BDTO getResult1 = actualFindAllResult.get(1);
    assertEquals("Many Ex Two", getResult1.getManyExTwo());
    assertEquals(123L, getResult1.getId().longValue());
    assertEquals(123L, getResult.getId().longValue());
    verify(this.bRepository).findAll();
  }

  /**
   * Method under test: {@link BService#get(Long)}
   */
  @Test
  void testGet() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());
    Optional<B> ofResult = Optional.of(b);
    when(this.bRepository.findById((Long) any())).thenReturn(ofResult);
    BDTO actualGetResult = this.bService.get(123L);
    assertEquals(123L, actualGetResult.getId().longValue());
    assertEquals("Many Ex Two", actualGetResult.getManyExTwo());
    verify(this.bRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link BService#get(Long)}
   */
  @Test
  void testGet2() {
    when(this.bRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> this.bService.get(123L));
    verify(this.bRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link BService#get(Long)}
   */
  @Test
  void testGet3() {
    when(this.bRepository.findById((Long) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.bService.get(123L));
    verify(this.bRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link BService#create(BDTO)}
   */
  @Test
  void testCreate() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());
    when(this.bRepository.save((B) any())).thenReturn(b);

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    assertEquals(123L, this.bService.create(bdto).longValue());
    verify(this.bRepository).save((B) any());
  }

  /**
   * Method under test: {@link BService#create(BDTO)}
   */
  @Test
  void testCreate2() {
    when(this.bRepository.save((B) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    assertThrows(ResponseStatusException.class, () -> this.bService.create(bdto));
    verify(this.bRepository).save((B) any());
  }

  /**
   * Method under test: {@link BService#update(Long, BDTO)}
   */
  @Test
  void testUpdate() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());
    Optional<B> ofResult = Optional.of(b);

    B b1 = new B();
    b1.setDateCreated(null);
    b1.setId(123L);
    b1.setLastUpdated(null);
    b1.setManyExTwo("Many Ex Two");
    b1.setMmFromHubToBHubs(new HashSet<>());
    when(this.bRepository.save((B) any())).thenReturn(b1);
    when(this.bRepository.findById((Long) any())).thenReturn(ofResult);

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    this.bService.update(123L, bdto);
    verify(this.bRepository).save((B) any());
    verify(this.bRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link BService#update(Long, BDTO)}
   */
  @Test
  void testUpdate2() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());
    Optional<B> ofResult = Optional.of(b);
    when(this.bRepository.save((B) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    when(this.bRepository.findById((Long) any())).thenReturn(ofResult);

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    assertThrows(ResponseStatusException.class, () -> this.bService.update(123L, bdto));
    verify(this.bRepository).save((B) any());
    verify(this.bRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link BService#update(Long, BDTO)}
   */
  @Test
  void testUpdate3() {
    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());
    when(this.bRepository.save((B) any())).thenReturn(b);
    when(this.bRepository.findById((Long) any())).thenReturn(Optional.empty());

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    assertThrows(ResponseStatusException.class, () -> this.bService.update(123L, bdto));
    verify(this.bRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link BService#delete(Long)}
   */
  @Test
  void testDelete() {
    doNothing().when(this.bRepository).deleteById((Long) any());
    this.bService.delete(123L);
    verify(this.bRepository).deleteById((Long) any());
  }

  /**
   * Method under test: {@link BService#delete(Long)}
   */
  @Test
  void testDelete2() {
    doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(this.bRepository)
        .deleteById((Long) any());
    assertThrows(ResponseStatusException.class, () -> this.bService.delete(123L));
    verify(this.bRepository).deleteById((Long) any());
  }
}

