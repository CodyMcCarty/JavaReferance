package io.cody.all_relationships.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.domain.NeedsBillingAddress;
import io.cody.all_relationships.model.NeedsBillingAddressDTO;
import io.cody.all_relationships.repos.NeedsBillingAddressRepository;
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

@ContextConfiguration(classes = {NeedsBillingAddressService.class})
@ExtendWith(SpringExtension.class)
class NeedsBillingAddressServiceTest {

  @MockBean
  private NeedsBillingAddressRepository needsBillingAddressRepository;

  @Autowired
  private NeedsBillingAddressService needsBillingAddressService;

  /**
   * Method under test: {@link NeedsBillingAddressService#findAll()}
   */
  @Test
  void testFindAll() {
    when(this.needsBillingAddressRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.needsBillingAddressService.findAll().isEmpty());
    verify(this.needsBillingAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#findAll()}
   */
  @Test
  void testFindAll2() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());

    ArrayList<NeedsBillingAddress> needsBillingAddressList = new ArrayList<>();
    needsBillingAddressList.add(needsBillingAddress);
    when(this.needsBillingAddressRepository.findAll()).thenReturn(needsBillingAddressList);
    List<NeedsBillingAddressDTO> actualFindAllResult = this.needsBillingAddressService.findAll();
    assertEquals(1, actualFindAllResult.size());
    NeedsBillingAddressDTO getResult = actualFindAllResult.get(0);
    assertEquals(123L, getResult.getId().longValue());
    assertEquals("42 Main St", getResult.getNeedsBillingAddress());
    verify(this.needsBillingAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#findAll()}
   */
  @Test
  void testFindAll3() {
    when(this.needsBillingAddressRepository.findAll()).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.needsBillingAddressService.findAll());
    verify(this.needsBillingAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#findAll()}
   */
  @Test
  void testFindAll4() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());

    NeedsBillingAddress needsBillingAddress1 = new NeedsBillingAddress();
    needsBillingAddress1.setDateCreated(null);
    needsBillingAddress1.setId(123L);
    needsBillingAddress1.setLastUpdated(null);
    needsBillingAddress1.setNeedsBillingAddress("42 Main St");
    needsBillingAddress1.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());

    ArrayList<NeedsBillingAddress> needsBillingAddressList = new ArrayList<>();
    needsBillingAddressList.add(needsBillingAddress1);
    needsBillingAddressList.add(needsBillingAddress);
    when(this.needsBillingAddressRepository.findAll()).thenReturn(needsBillingAddressList);
    List<NeedsBillingAddressDTO> actualFindAllResult = this.needsBillingAddressService.findAll();
    assertEquals(2, actualFindAllResult.size());
    NeedsBillingAddressDTO getResult = actualFindAllResult.get(0);
    assertEquals("42 Main St", getResult.getNeedsBillingAddress());
    NeedsBillingAddressDTO getResult1 = actualFindAllResult.get(1);
    assertEquals("42 Main St", getResult1.getNeedsBillingAddress());
    assertEquals(123L, getResult1.getId().longValue());
    assertEquals(123L, getResult.getId().longValue());
    verify(this.needsBillingAddressRepository).findAll();
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#get(Long)}
   */
  @Test
  void testGet() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    Optional<NeedsBillingAddress> ofResult = Optional.of(needsBillingAddress);
    when(this.needsBillingAddressRepository.findById((Long) any())).thenReturn(ofResult);
    NeedsBillingAddressDTO actualGetResult = this.needsBillingAddressService.get(123L);
    assertEquals(123L, actualGetResult.getId().longValue());
    assertEquals("42 Main St", actualGetResult.getNeedsBillingAddress());
    verify(this.needsBillingAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#get(Long)}
   */
  @Test
  void testGet2() {
    when(this.needsBillingAddressRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> this.needsBillingAddressService.get(123L));
    verify(this.needsBillingAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#get(Long)}
   */
  @Test
  void testGet3() {
    when(this.needsBillingAddressRepository.findById((Long) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.needsBillingAddressService.get(123L));
    verify(this.needsBillingAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#create(NeedsBillingAddressDTO)}
   */
  @Test
  void testCreate() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    when(this.needsBillingAddressRepository.save((NeedsBillingAddress) any())).thenReturn(
        needsBillingAddress);

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    assertEquals(123L, this.needsBillingAddressService.create(needsBillingAddressDTO).longValue());
    verify(this.needsBillingAddressRepository).save((NeedsBillingAddress) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#create(NeedsBillingAddressDTO)}
   */
  @Test
  void testCreate2() {
    when(this.needsBillingAddressRepository.save((NeedsBillingAddress) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    assertThrows(ResponseStatusException.class,
        () -> this.needsBillingAddressService.create(needsBillingAddressDTO));
    verify(this.needsBillingAddressRepository).save((NeedsBillingAddress) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#update(Long, NeedsBillingAddressDTO)}
   */
  @Test
  void testUpdate() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    Optional<NeedsBillingAddress> ofResult = Optional.of(needsBillingAddress);

    NeedsBillingAddress needsBillingAddress1 = new NeedsBillingAddress();
    needsBillingAddress1.setDateCreated(null);
    needsBillingAddress1.setId(123L);
    needsBillingAddress1.setLastUpdated(null);
    needsBillingAddress1.setNeedsBillingAddress("42 Main St");
    needsBillingAddress1.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    when(this.needsBillingAddressRepository.save((NeedsBillingAddress) any())).thenReturn(
        needsBillingAddress1);
    when(this.needsBillingAddressRepository.findById((Long) any())).thenReturn(ofResult);

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    this.needsBillingAddressService.update(123L, needsBillingAddressDTO);
    verify(this.needsBillingAddressRepository).save((NeedsBillingAddress) any());
    verify(this.needsBillingAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#update(Long, NeedsBillingAddressDTO)}
   */
  @Test
  void testUpdate2() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    Optional<NeedsBillingAddress> ofResult = Optional.of(needsBillingAddress);
    when(this.needsBillingAddressRepository.save((NeedsBillingAddress) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    when(this.needsBillingAddressRepository.findById((Long) any())).thenReturn(ofResult);

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    assertThrows(ResponseStatusException.class,
        () -> this.needsBillingAddressService.update(123L, needsBillingAddressDTO));
    verify(this.needsBillingAddressRepository).save((NeedsBillingAddress) any());
    verify(this.needsBillingAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#update(Long, NeedsBillingAddressDTO)}
   */
  @Test
  void testUpdate3() {
    NeedsBillingAddress needsBillingAddress = new NeedsBillingAddress();
    needsBillingAddress.setDateCreated(null);
    needsBillingAddress.setId(123L);
    needsBillingAddress.setLastUpdated(null);
    needsBillingAddress.setNeedsBillingAddress("42 Main St");
    needsBillingAddress.setOmFNeedsBillAddTBillAddBillingAddresss(new HashSet<>());
    when(this.needsBillingAddressRepository.save((NeedsBillingAddress) any())).thenReturn(
        needsBillingAddress);
    when(this.needsBillingAddressRepository.findById((Long) any())).thenReturn(Optional.empty());

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    assertThrows(ResponseStatusException.class,
        () -> this.needsBillingAddressService.update(123L, needsBillingAddressDTO));
    verify(this.needsBillingAddressRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#delete(Long)}
   */
  @Test
  void testDelete() {
    doNothing().when(this.needsBillingAddressRepository).deleteById((Long) any());
    this.needsBillingAddressService.delete(123L);
    verify(this.needsBillingAddressRepository).deleteById((Long) any());
  }

  /**
   * Method under test: {@link NeedsBillingAddressService#delete(Long)}
   */
  @Test
  void testDelete2() {
    doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(
            this.needsBillingAddressRepository)
        .deleteById((Long) any());
    assertThrows(ResponseStatusException.class, () -> this.needsBillingAddressService.delete(123L));
    verify(this.needsBillingAddressRepository).deleteById((Long) any());
  }
}

