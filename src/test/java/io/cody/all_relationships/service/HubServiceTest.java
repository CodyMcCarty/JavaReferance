package io.cody.all_relationships.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.domain.B;
import io.cody.all_relationships.domain.Hub;
import io.cody.all_relationships.domain.X;
import io.cody.all_relationships.domain.Y;
import io.cody.all_relationships.domain.Z;
import io.cody.all_relationships.model.Clearnance;
import io.cody.all_relationships.model.HubDTO;
import io.cody.all_relationships.repos.BRepository;
import io.cody.all_relationships.repos.HubRepository;
import io.cody.all_relationships.repos.XRepository;
import io.cody.all_relationships.repos.YRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {HubService.class})
@ExtendWith(SpringExtension.class)
class HubServiceTest {

  @MockBean
  private BRepository bRepository;

  @MockBean
  private HubRepository hubRepository;

  @Autowired
  private HubService hubService;

  @MockBean
  private XRepository xRepository;

  @MockBean
  private YRepository yRepository;

  /**
   * Method under test: {@link HubService#findAll()}
   */
  @Test
  void testFindAll() {
    when(this.hubRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.hubService.findAll().isEmpty());
    verify(this.hubRepository).findAll();
  }

  /**
   * Method under test: {@link HubService#findAll()}
   */
  @Test
  void testFindAll2() {
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    UUID randomUUIDResult = UUID.randomUUID();
    hub.setId(randomUUIDResult);
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);

    ArrayList<Hub> hubList = new ArrayList<>();
    hubList.add(hub);
    when(this.hubRepository.findAll()).thenReturn(hubList);
    List<HubDTO> actualFindAllResult = this.hubService.findAll();
    assertEquals(1, actualFindAllResult.size());
    HubDTO getResult = actualFindAllResult.get(0);
    assertSame(randomUUIDResult, getResult.getId());
    assertEquals(123L, getResult.getOmFYTHub().longValue());
    assertEquals("Many Ex One", getResult.getManyExOne());
    assertEquals(123L, getResult.getMoFHubTX().longValue());
    assertTrue(getResult.getMmFromHubToBs().isEmpty());
    verify(this.hubRepository).findAll();
  }

  /**
   * Method under test: {@link HubService#findAll()}
   */
  @Test
  void testFindAll3() {
    when(this.hubRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.hubService.findAll());
    verify(this.hubRepository).findAll();
  }

  /**
   * Method under test: {@link HubService#findAll()}
   */
  @Test
  void testFindAll4() {
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    UUID randomUUIDResult = UUID.randomUUID();
    hub.setId(randomUUIDResult);
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z1);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    UUID randomUUIDResult1 = UUID.randomUUID();
    hub1.setId(randomUUIDResult1);
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x3);
    hub1.setOmFYTHub(y1);

    ArrayList<Hub> hubList = new ArrayList<>();
    hubList.add(hub1);
    hubList.add(hub);
    when(this.hubRepository.findAll()).thenReturn(hubList);
    List<HubDTO> actualFindAllResult = this.hubService.findAll();
    assertEquals(2, actualFindAllResult.size());
    HubDTO getResult = actualFindAllResult.get(0);
    assertEquals(123L, getResult.getOmFYTHub().longValue());
    HubDTO getResult1 = actualFindAllResult.get(1);
    assertEquals(123L, getResult1.getOmFYTHub().longValue());
    assertEquals(123L, getResult1.getMoFHubTX().longValue());
    List<Long> mmFromHubToBs = getResult1.getMmFromHubToBs();
    assertTrue(mmFromHubToBs.isEmpty());
    assertEquals("Many Ex One", getResult1.getManyExOne());
    assertSame(randomUUIDResult, getResult1.getId());
    assertEquals(123L, getResult.getMoFHubTX().longValue());
    assertSame(randomUUIDResult1, getResult.getId());
    assertEquals(mmFromHubToBs, getResult.getMmFromHubToBs());
    assertEquals("Many Ex One", getResult.getManyExOne());
    verify(this.hubRepository).findAll();
  }

  /**
   * Method under test: {@link HubService#findAll()}
   */
  @Test
  void testFindAll5() {
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z1);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x3);

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z2);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());

    HashSet<B> bSet = new HashSet<>();
    bSet.add(b);
    Hub hub = mock(Hub.class);
    when(hub.getMoFHubTX()).thenReturn(x4);
    when(hub.getOmFYTHub()).thenReturn(y1);
    when(hub.getManyExOne()).thenReturn("Many Ex One");
    when(hub.getMmFromHubToBBs()).thenReturn(bSet);
    UUID randomUUIDResult = UUID.randomUUID();
    when(hub.getId()).thenReturn(randomUUIDResult);
    doNothing().when(hub).setDateCreated((OffsetDateTime) any());
    doNothing().when(hub).setId((UUID) any());
    doNothing().when(hub).setLastUpdated((OffsetDateTime) any());
    doNothing().when(hub).setManyExOne((String) any());
    doNothing().when(hub).setMmFromHubToBBs((Set<B>) any());
    doNothing().when(hub).setMoFHubTX((X) any());
    doNothing().when(hub).setOmFYTHub((Y) any());
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);

    ArrayList<Hub> hubList = new ArrayList<>();
    hubList.add(hub);
    when(this.hubRepository.findAll()).thenReturn(hubList);
    List<HubDTO> actualFindAllResult = this.hubService.findAll();
    assertEquals(1, actualFindAllResult.size());
    HubDTO getResult = actualFindAllResult.get(0);
    assertSame(randomUUIDResult, getResult.getId());
    assertEquals(123L, getResult.getOmFYTHub().longValue());
    assertEquals("Many Ex One", getResult.getManyExOne());
    assertEquals(123L, getResult.getMoFHubTX().longValue());
    assertEquals(1, getResult.getMmFromHubToBs().size());
    verify(this.hubRepository).findAll();
    verify(hub, atLeast(1)).getMoFHubTX();
    verify(hub, atLeast(1)).getOmFYTHub();
    verify(hub).getManyExOne();
    verify(hub, atLeast(1)).getMmFromHubToBBs();
    verify(hub).getId();
    verify(hub).setDateCreated((OffsetDateTime) any());
    verify(hub).setId((UUID) any());
    verify(hub).setLastUpdated((OffsetDateTime) any());
    verify(hub).setManyExOne((String) any());
    verify(hub).setMmFromHubToBBs((Set<B>) any());
    verify(hub).setMoFHubTX((X) any());
    verify(hub).setOmFYTHub((Y) any());
  }

  /**
   * Method under test: {@link HubService#get(UUID)}
   */
  @Test
  void testGet() {
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    UUID randomUUIDResult = UUID.randomUUID();
    hub.setId(randomUUIDResult);
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);
    Optional<Hub> ofResult = Optional.of(hub);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult);
    HubDTO actualGetResult = this.hubService.get(UUID.randomUUID());
    assertSame(randomUUIDResult, actualGetResult.getId());
    assertEquals(123L, actualGetResult.getOmFYTHub().longValue());
    assertEquals("Many Ex One", actualGetResult.getManyExOne());
    assertEquals(123L, actualGetResult.getMoFHubTX().longValue());
    assertTrue(actualGetResult.getMmFromHubToBs().isEmpty());
    verify(this.hubRepository).findById((UUID) any());
  }

  /**
   * Method under test: {@link HubService#get(UUID)}
   */
  @Test
  void testGet2() {
    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z1);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x3);

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z2);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    B b = new B();
    b.setDateCreated(null);
    b.setId(123L);
    b.setLastUpdated(null);
    b.setManyExTwo("Many Ex Two");
    b.setMmFromHubToBHubs(new HashSet<>());

    HashSet<B> bSet = new HashSet<>();
    bSet.add(b);
    Hub hub = mock(Hub.class);
    when(hub.getMoFHubTX()).thenReturn(x4);
    when(hub.getOmFYTHub()).thenReturn(y1);
    when(hub.getManyExOne()).thenReturn("Many Ex One");
    when(hub.getMmFromHubToBBs()).thenReturn(bSet);
    UUID randomUUIDResult = UUID.randomUUID();
    when(hub.getId()).thenReturn(randomUUIDResult);
    doNothing().when(hub).setDateCreated((OffsetDateTime) any());
    doNothing().when(hub).setId((UUID) any());
    doNothing().when(hub).setLastUpdated((OffsetDateTime) any());
    doNothing().when(hub).setManyExOne((String) any());
    doNothing().when(hub).setMmFromHubToBBs((Set<B>) any());
    doNothing().when(hub).setMoFHubTX((X) any());
    doNothing().when(hub).setOmFYTHub((Y) any());
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);
    Optional<Hub> ofResult = Optional.of(hub);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult);
    HubDTO actualGetResult = this.hubService.get(UUID.randomUUID());
    assertSame(randomUUIDResult, actualGetResult.getId());
    assertEquals(123L, actualGetResult.getOmFYTHub().longValue());
    assertEquals("Many Ex One", actualGetResult.getManyExOne());
    assertEquals(123L, actualGetResult.getMoFHubTX().longValue());
    assertEquals(1, actualGetResult.getMmFromHubToBs().size());
    verify(this.hubRepository).findById((UUID) any());
    verify(hub, atLeast(1)).getMoFHubTX();
    verify(hub, atLeast(1)).getOmFYTHub();
    verify(hub).getManyExOne();
    verify(hub, atLeast(1)).getMmFromHubToBBs();
    verify(hub).getId();
    verify(hub).setDateCreated((OffsetDateTime) any());
    verify(hub).setId((UUID) any());
    verify(hub).setLastUpdated((OffsetDateTime) any());
    verify(hub).setManyExOne((String) any());
    verify(hub).setMmFromHubToBBs((Set<B>) any());
    verify(hub).setMoFHubTX((X) any());
    verify(hub).setOmFYTHub((Y) any());
  }

  /**
   * Method under test: {@link HubService#create(HubDTO)}
   */
  @Test
  void testCreate() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult1 = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult1);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(new X());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z2);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z3);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    UUID randomUUIDResult = UUID.randomUUID();
    hub.setId(randomUUIDResult);
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y1);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertSame(randomUUIDResult, this.hubService.create(hubDTO));
    verify(this.yRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
    verify(this.hubRepository).save((Hub) any());
    verify(this.bRepository).findAllById((Iterable<Long>) any());
  }

  /**
   * Method under test: {@link HubService#create(HubDTO)}
   */
  @Test
  void testCreate2() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult1 = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult1);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(new X());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z2);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z3);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y1);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub);
    when(this.bRepository.findAllById((Iterable<Long>) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertThrows(ResponseStatusException.class, () -> this.hubService.create(hubDTO));
    verify(this.yRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
    verify(this.bRepository).findAllById((Iterable<Long>) any());
  }

  /**
   * Method under test: {@link HubService#create(HubDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreate3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "io.cody.all_relationships.repos.YRepository.findById(Object)" is null
    //       at io.cody.all_relationships.service.HubService.mapToEntity(HubService.java:88)
    //       at io.cody.all_relationships.service.HubService.create(HubService.java:53)
    //   In order to prevent create(HubDTO)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   create(HubDTO).
    //   See https://diff.blue/R013 to resolve this issue.

    when(this.yRepository.findById((Long) any())).thenReturn(null);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(new X());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z2);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z3);

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    this.hubService.create(hubDTO);
  }

  /**
   * Method under test: {@link HubService#create(HubDTO)}
   */
  @Test
  void testCreate4() {
    when(this.yRepository.findById((Long) any())).thenReturn(Optional.empty());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(new X());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z2);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z3);

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertThrows(ResponseStatusException.class, () -> this.hubService.create(hubDTO));
    verify(this.yRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link HubService#create(HubDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreate5() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "io.cody.all_relationships.repos.XRepository.findById(Object)" is null
    //       at io.cody.all_relationships.service.HubService.mapToEntity(HubService.java:83)
    //       at io.cody.all_relationships.service.HubService.create(HubService.java:53)
    //   In order to prevent create(HubDTO)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   create(HubDTO).
    //   See https://diff.blue/R013 to resolve this issue.

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);
    when(this.xRepository.findById((Long) any())).thenReturn(null);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y1);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    this.hubService.create(hubDTO);
  }

  /**
   * Method under test: {@link HubService#create(HubDTO)}
   */
  @Test
  void testCreate6() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);
    when(this.xRepository.findById((Long) any())).thenReturn(Optional.empty());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y1);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertThrows(ResponseStatusException.class, () -> this.hubService.create(hubDTO));
    verify(this.xRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link HubService#update(UUID, HubDTO)}
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
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult1 = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult1);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z2);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y1);
    Optional<Hub> ofResult2 = Optional.of(hub);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(new X());

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);

    Z z4 = new Z();
    z4.setDateCreated(null);
    z4.setHasOneToOne("Has One To One");
    z4.setId(123L);
    z4.setLastUpdated(null);
    z4.setOoFXTZ(x4);

    X x5 = new X();
    x5.setClearance(Clearnance.VIEWER);
    x5.setDateCreated(null);
    x5.setHasManyToOneRel("Has Many To One Rel");
    x5.setHasOneToOne("Has One To One");
    x5.setId(123L);
    x5.setLastUpdated(null);
    x5.setMoFHubTXHubs(new HashSet<>());
    x5.setOoFXTZ(z4);

    Y y2 = new Y();
    y2.setDateCreated(null);
    y2.setHasOneToManyRel("Has One To Many Rel");
    y2.setId(123L);
    y2.setLastUpdated(null);
    y2.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    hub1.setId(UUID.randomUUID());
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x5);
    hub1.setOmFYTHub(y2);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub1);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult2);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());
    UUID id = UUID.randomUUID();

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    this.hubService.update(id, hubDTO);
    verify(this.yRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
    verify(this.hubRepository).save((Hub) any());
    verify(this.hubRepository).findById((UUID) any());
    verify(this.bRepository).findAllById((Iterable<Long>) any());
  }

  /**
   * Method under test: {@link HubService#update(UUID, HubDTO)}
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
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult1 = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult1);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z2);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y1);
    Optional<Hub> ofResult2 = Optional.of(hub);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(new X());

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);

    Z z4 = new Z();
    z4.setDateCreated(null);
    z4.setHasOneToOne("Has One To One");
    z4.setId(123L);
    z4.setLastUpdated(null);
    z4.setOoFXTZ(x4);

    X x5 = new X();
    x5.setClearance(Clearnance.VIEWER);
    x5.setDateCreated(null);
    x5.setHasManyToOneRel("Has Many To One Rel");
    x5.setHasOneToOne("Has One To One");
    x5.setId(123L);
    x5.setLastUpdated(null);
    x5.setMoFHubTXHubs(new HashSet<>());
    x5.setOoFXTZ(z4);

    Y y2 = new Y();
    y2.setDateCreated(null);
    y2.setHasOneToManyRel("Has One To Many Rel");
    y2.setId(123L);
    y2.setLastUpdated(null);
    y2.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    hub1.setId(UUID.randomUUID());
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x5);
    hub1.setOmFYTHub(y2);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub1);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult2);
    when(this.bRepository.findAllById((Iterable<Long>) any()))
        .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    UUID id = UUID.randomUUID();

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertThrows(ResponseStatusException.class, () -> this.hubService.update(id, hubDTO));
    verify(this.yRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
    verify(this.hubRepository).findById((UUID) any());
    verify(this.bRepository).findAllById((Iterable<Long>) any());
  }

  /**
   * Method under test: {@link HubService#update(UUID, HubDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testUpdate3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "io.cody.all_relationships.repos.YRepository.findById(Object)" is null
    //       at io.cody.all_relationships.service.HubService.mapToEntity(HubService.java:88)
    //       at io.cody.all_relationships.service.HubService.update(HubService.java:60)
    //   In order to prevent update(UUID, HubDTO)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   update(UUID, HubDTO).
    //   See https://diff.blue/R013 to resolve this issue.

    when(this.yRepository.findById((Long) any())).thenReturn(null);

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z2);

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y);
    Optional<Hub> ofResult1 = Optional.of(hub);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(new X());

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);

    Z z4 = new Z();
    z4.setDateCreated(null);
    z4.setHasOneToOne("Has One To One");
    z4.setId(123L);
    z4.setLastUpdated(null);
    z4.setOoFXTZ(x4);

    X x5 = new X();
    x5.setClearance(Clearnance.VIEWER);
    x5.setDateCreated(null);
    x5.setHasManyToOneRel("Has Many To One Rel");
    x5.setHasOneToOne("Has One To One");
    x5.setId(123L);
    x5.setLastUpdated(null);
    x5.setMoFHubTXHubs(new HashSet<>());
    x5.setOoFXTZ(z4);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    hub1.setId(UUID.randomUUID());
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x5);
    hub1.setOmFYTHub(y1);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub1);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult1);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());
    UUID id = UUID.randomUUID();

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    this.hubService.update(id, hubDTO);
  }

  /**
   * Method under test: {@link HubService#update(UUID, HubDTO)}
   */
  @Test
  void testUpdate4() {
    when(this.yRepository.findById((Long) any())).thenReturn(Optional.empty());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
    z.setOoFXTZ(new X());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(z);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x);

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z1);
    Optional<X> ofResult = Optional.of(x1);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(new Z());

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z2);

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x3);
    hub.setOmFYTHub(y);
    Optional<Hub> ofResult1 = Optional.of(hub);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(new X());

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);

    Z z4 = new Z();
    z4.setDateCreated(null);
    z4.setHasOneToOne("Has One To One");
    z4.setId(123L);
    z4.setLastUpdated(null);
    z4.setOoFXTZ(x4);

    X x5 = new X();
    x5.setClearance(Clearnance.VIEWER);
    x5.setDateCreated(null);
    x5.setHasManyToOneRel("Has Many To One Rel");
    x5.setHasOneToOne("Has One To One");
    x5.setId(123L);
    x5.setLastUpdated(null);
    x5.setMoFHubTXHubs(new HashSet<>());
    x5.setOoFXTZ(z4);

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    hub1.setId(UUID.randomUUID());
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x5);
    hub1.setOmFYTHub(y1);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub1);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult1);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());
    UUID id = UUID.randomUUID();

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertThrows(ResponseStatusException.class, () -> this.hubService.update(id, hubDTO));
    verify(this.yRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
    verify(this.hubRepository).findById((UUID) any());
  }

  /**
   * Method under test: {@link HubService#update(UUID, HubDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testUpdate5() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "io.cody.all_relationships.repos.XRepository.findById(Object)" is null
    //       at io.cody.all_relationships.service.HubService.mapToEntity(HubService.java:83)
    //       at io.cody.all_relationships.service.HubService.update(HubService.java:60)
    //   In order to prevent update(UUID, HubDTO)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   update(UUID, HubDTO).
    //   See https://diff.blue/R013 to resolve this issue.

    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);
    when(this.xRepository.findById((Long) any())).thenReturn(null);

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y1);
    Optional<Hub> ofResult1 = Optional.of(hub);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(new X());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z1);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z2);

    Y y2 = new Y();
    y2.setDateCreated(null);
    y2.setHasOneToManyRel("Has One To Many Rel");
    y2.setId(123L);
    y2.setLastUpdated(null);
    y2.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    hub1.setId(UUID.randomUUID());
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x3);
    hub1.setOmFYTHub(y2);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub1);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult1);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());
    UUID id = UUID.randomUUID();

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    this.hubService.update(id, hubDTO);
  }

  /**
   * Method under test: {@link HubService#update(UUID, HubDTO)}
   */
  @Test
  void testUpdate6() {
    Y y = new Y();
    y.setDateCreated(null);
    y.setHasOneToManyRel("Has One To Many Rel");
    y.setId(123L);
    y.setLastUpdated(null);
    y.setOmFYTHubHubs(new HashSet<>());
    Optional<Y> ofResult = Optional.of(y);
    when(this.yRepository.findById((Long) any())).thenReturn(ofResult);
    when(this.xRepository.findById((Long) any())).thenReturn(Optional.empty());

    X x = new X();
    x.setClearance(Clearnance.VIEWER);
    x.setDateCreated(null);
    x.setHasManyToOneRel("Has Many To One Rel");
    x.setHasOneToOne("Has One To One");
    x.setId(123L);
    x.setLastUpdated(null);
    x.setMoFHubTXHubs(new HashSet<>());
    x.setOoFXTZ(new Z());

    Z z = new Z();
    z.setDateCreated(null);
    z.setHasOneToOne("Has One To One");
    z.setId(123L);
    z.setLastUpdated(null);
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

    Y y1 = new Y();
    y1.setDateCreated(null);
    y1.setHasOneToManyRel("Has One To Many Rel");
    y1.setId(123L);
    y1.setLastUpdated(null);
    y1.setOmFYTHubHubs(new HashSet<>());

    Hub hub = new Hub();
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y1);
    Optional<Hub> ofResult1 = Optional.of(hub);

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(new X());

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z1);

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(x2);

    X x3 = new X();
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z2);

    Y y2 = new Y();
    y2.setDateCreated(null);
    y2.setHasOneToManyRel("Has One To Many Rel");
    y2.setId(123L);
    y2.setLastUpdated(null);
    y2.setOmFYTHubHubs(new HashSet<>());

    Hub hub1 = new Hub();
    hub1.setDateCreated(null);
    hub1.setId(UUID.randomUUID());
    hub1.setLastUpdated(null);
    hub1.setManyExOne("Many Ex One");
    hub1.setMmFromHubToBBs(new HashSet<>());
    hub1.setMoFHubTX(x3);
    hub1.setOmFYTHub(y2);
    when(this.hubRepository.save((Hub) any())).thenReturn(hub1);
    when(this.hubRepository.findById((UUID) any())).thenReturn(ofResult1);
    when(this.bRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<>());
    UUID id = UUID.randomUUID();

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    assertThrows(ResponseStatusException.class, () -> this.hubService.update(id, hubDTO));
    verify(this.xRepository).findById((Long) any());
    verify(this.hubRepository).findById((UUID) any());
  }

  /**
   * Method under test: {@link HubService#delete(UUID)}
   */
  @Test
  void testDelete() {
    doNothing().when(this.hubRepository).deleteById((UUID) any());
    this.hubService.delete(UUID.randomUUID());
    verify(this.hubRepository).deleteById((UUID) any());
  }

  /**
   * Method under test: {@link HubService#delete(UUID)}
   */
  @Test
  void testDelete2() {
    doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(this.hubRepository)
        .deleteById((UUID) any());
    assertThrows(ResponseStatusException.class, () -> this.hubService.delete(UUID.randomUUID()));
    verify(this.hubRepository).deleteById((UUID) any());
  }
}

