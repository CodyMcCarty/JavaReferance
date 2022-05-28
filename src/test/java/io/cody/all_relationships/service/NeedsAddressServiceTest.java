package io.cody.all_relationships.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.domain.NeedsAddress;
import io.cody.all_relationships.model.NeedsAddressDTO;
import io.cody.all_relationships.repos.NeedsAddressRepository;
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

@ContextConfiguration(classes = {NeedsAddressService.class})
@ExtendWith(SpringExtension.class)
class NeedsAddressServiceTest {

  @MockBean
  private NeedsAddressRepository needsAddressRepository;

  @Autowired
  private NeedsAddressService needsAddressService;

  /**
   * Method under test: {@link NeedsAddressService#findAll()}
   */
  @Test
  void testFindAll() {
    when(this.needsAddressRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.needsAddressService.findAll().isEmpty());
    verify(this.needsAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsAddressService#findAll()}
   */
  @Test
  void testFindAll2() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");

    ArrayList<NeedsAddress> needsAddressList = new ArrayList<>();
    needsAddressList.add(needsAddress);
    when(this.needsAddressRepository.findAll()).thenReturn(needsAddressList);
    List<NeedsAddressDTO> actualFindAllResult = this.needsAddressService.findAll();
    assertEquals(1, actualFindAllResult.size());
    NeedsAddressDTO getResult = actualFindAllResult.get(0);
    assertEquals(123L, getResult.getId().longValue());
    assertEquals("42 Main St", getResult.getNeedsAddress());
    verify(this.needsAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsAddressService#findAll()}
   */
  @Test
  void testFindAll3() {
    when(this.needsAddressRepository.findAll()).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.needsAddressService.findAll());
    verify(this.needsAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsAddressService#findAll()}
   */
  @Test
  void testFindAll4() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");

    NeedsAddress needsAddress1 = new NeedsAddress();
    needsAddress1.setDateCreated(null);
    needsAddress1.setId(123L);
    needsAddress1.setLastUpdated(null);
    needsAddress1.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress1.setNeedsAddress("42 Main St");

    ArrayList<NeedsAddress> needsAddressList = new ArrayList<>();
    needsAddressList.add(needsAddress1);
    needsAddressList.add(needsAddress);
    when(this.needsAddressRepository.findAll()).thenReturn(needsAddressList);
    List<NeedsAddressDTO> actualFindAllResult = this.needsAddressService.findAll();
    assertEquals(2, actualFindAllResult.size());
    NeedsAddressDTO getResult = actualFindAllResult.get(0);
    assertEquals("42 Main St", getResult.getNeedsAddress());
    NeedsAddressDTO getResult1 = actualFindAllResult.get(1);
    assertEquals("42 Main St", getResult1.getNeedsAddress());
    assertEquals(123L, getResult1.getId().longValue());
    assertEquals(123L, getResult.getId().longValue());
    verify(this.needsAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsAddressService#get(Long)}
   */
  @Test
  void testGet() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");
    Optional<NeedsAddress> ofResult = Optional.of(needsAddress);
    when(this.needsAddressRepository.findById((Long) any())).thenReturn(ofResult);
    NeedsAddressDTO actualGetResult = this.needsAddressService.get(123L);
    assertEquals(123L, actualGetResult.getId().longValue());
    assertEquals("42 Main St", actualGetResult.getNeedsAddress());
    verify(this.needsAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#get(Long)}
   */
  @Test
  void testGet2() {
    when(this.needsAddressRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> this.needsAddressService.get(123L));
    verify(this.needsAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#get(Long)}
   */
  @Test
  void testGet3() {
    when(this.needsAddressRepository.findById((Long) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.needsAddressService.get(123L));
    verify(this.needsAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#create(NeedsAddressDTO)}
   */
  @Test
  void testCreate() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");
    when(this.needsAddressRepository.save((NeedsAddress) any())).thenReturn(needsAddress);

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    assertEquals(123L, this.needsAddressService.create(needsAddressDTO).longValue());
    verify(this.needsAddressRepository).save((NeedsAddress) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#create(NeedsAddressDTO)}
   */
  @Test
  void testCreate2() {
    when(this.needsAddressRepository.save((NeedsAddress) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    assertThrows(ResponseStatusException.class,
        () -> this.needsAddressService.create(needsAddressDTO));
    verify(this.needsAddressRepository).save((NeedsAddress) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#update(Long, NeedsAddressDTO)}
   */
  @Test
  void testUpdate() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");
    Optional<NeedsAddress> ofResult = Optional.of(needsAddress);

    NeedsAddress needsAddress1 = new NeedsAddress();
    needsAddress1.setDateCreated(null);
    needsAddress1.setId(123L);
    needsAddress1.setLastUpdated(null);
    needsAddress1.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress1.setNeedsAddress("42 Main St");
    when(this.needsAddressRepository.save((NeedsAddress) any())).thenReturn(needsAddress1);
    when(this.needsAddressRepository.findById((Long) any())).thenReturn(ofResult);

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    this.needsAddressService.update(123L, needsAddressDTO);
    verify(this.needsAddressRepository).save((NeedsAddress) any());
    verify(this.needsAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#update(Long, NeedsAddressDTO)}
   */
  @Test
  void testUpdate2() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");
    Optional<NeedsAddress> ofResult = Optional.of(needsAddress);
    when(this.needsAddressRepository.save((NeedsAddress) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    when(this.needsAddressRepository.findById((Long) any())).thenReturn(ofResult);

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    assertThrows(ResponseStatusException.class,
        () -> this.needsAddressService.update(123L, needsAddressDTO));
    verify(this.needsAddressRepository).save((NeedsAddress) any());
    verify(this.needsAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#update(Long, NeedsAddressDTO)}
   */
  @Test
  void testUpdate3() {
    NeedsAddress needsAddress = new NeedsAddress();
    needsAddress.setDateCreated(null);
    needsAddress.setId(123L);
    needsAddress.setLastUpdated(null);
    needsAddress.setMoFAddTNeedsAddAddresss(new HashSet<>());
    needsAddress.setNeedsAddress("42 Main St");
    when(this.needsAddressRepository.save((NeedsAddress) any())).thenReturn(needsAddress);
    when(this.needsAddressRepository.findById((Long) any())).thenReturn(Optional.empty());

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    assertThrows(ResponseStatusException.class,
        () -> this.needsAddressService.update(123L, needsAddressDTO));
    verify(this.needsAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#delete(Long)}
   */
  @Test
  void testDelete() {
    doNothing().when(this.needsAddressRepository).deleteById((Long) any());
    this.needsAddressService.delete(123L);
    verify(this.needsAddressRepository).deleteById((Long) any());
  }

  /**
   * Method under test: {@link NeedsAddressService#delete(Long)}
   */
  @Test
  void testDelete2() {
    doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(this.needsAddressRepository)
        .deleteById((Long) any());
    assertThrows(ResponseStatusException.class, () -> this.needsAddressService.delete(123L));
    verify(this.needsAddressRepository).deleteById((Long) any());
  }
}

