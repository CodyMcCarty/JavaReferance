package io.cody.all_relationships.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import io.cody.all_relationships.service.HubService;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HubResource.class})
@ExtendWith(SpringExtension.class)
class HubResourceTest {

  @Autowired
  private HubResource hubResource;

  @MockBean
  private HubService hubService;

  /**
   * Method under test: {@link HubResource#createHub(HubDTO)}
   */
  @Test
  void testCreateHub() throws Exception {
    when(this.hubService.findAll()).thenReturn(new ArrayList<>());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    String content = (new ObjectMapper()).writeValueAsString(hubDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/hubs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.hubResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link HubResource#getHub(UUID)}
   */
  @Test
  void testGetHub() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R015 Method may be nondeterministic.
    //   Diffblue Cover tried to run the created test twice, but it first passed the
    //   assertions and then failed.
    //   See https://diff.blue/R015 to resolve this issue.

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
    HubRepository hubRepository = mock(HubRepository.class);
    when(hubRepository.findById((UUID) any())).thenReturn(Optional.of(hub));
    HubResource hubResource = new HubResource(
        new HubService(hubRepository, mock(XRepository.class), mock(YRepository.class),
            mock(BRepository.class)));
    ResponseEntity<HubDTO> actualHub = hubResource.getHub(UUID.randomUUID());
    assertTrue(actualHub.hasBody());
    assertTrue(actualHub.getHeaders().isEmpty());
    assertEquals(HttpStatus.OK, actualHub.getStatusCode());
    HubDTO body = actualHub.getBody();
    assertEquals("Many Ex One", body.getManyExOne());
    assertSame(randomUUIDResult, body.getId());
    assertEquals(123L, body.getOmFYTHub().longValue());
    assertEquals(123L, body.getMoFHubTX().longValue());
    assertTrue(body.getMmFromHubToBs().isEmpty());
    verify(hubRepository).findById((UUID) any());
  }

  /**
   * Method under test: {@link HubResource#getHub(UUID)}
   */
  @Test
  void testGetHub2() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R015 Method may be nondeterministic.
    //   Diffblue Cover tried to run the created test twice, but it first passed the
    //   assertions and then failed.
    //   See https://diff.blue/R015 to resolve this issue.

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
    HubRepository hubRepository = mock(HubRepository.class);
    when(hubRepository.findById((UUID) any())).thenReturn(Optional.of(hub));
    HubResource hubResource = new HubResource(
        new HubService(hubRepository, mock(XRepository.class), mock(YRepository.class),
            mock(BRepository.class)));
    ResponseEntity<HubDTO> actualHub = hubResource.getHub(UUID.randomUUID());
    assertTrue(actualHub.hasBody());
    assertTrue(actualHub.getHeaders().isEmpty());
    assertEquals(HttpStatus.OK, actualHub.getStatusCode());
    HubDTO body = actualHub.getBody();
    assertEquals("Many Ex One", body.getManyExOne());
    assertSame(randomUUIDResult, body.getId());
    assertEquals(123L, body.getOmFYTHub().longValue());
    assertEquals(123L, body.getMoFHubTX().longValue());
    assertEquals(1, body.getMmFromHubToBs().size());
    verify(hubRepository).findById((UUID) any());
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
   * Method under test: {@link HubResource#getHub(UUID)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetHub3() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R015 Method may be nondeterministic.
    //   Diffblue Cover tried to run the created test twice, but it first passed the
    //   assertions and then failed.
    //   See https://diff.blue/R015 to resolve this issue.

    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.springframework.web.server.ResponseStatusException: 404 NOT_FOUND
    //       at io.cody.all_relationships.service.HubService.lambda$get$2(HubService.java:48)
    //       at java.util.Optional.orElseThrow(Optional.java:403)
    //       at io.cody.all_relationships.service.HubService.get(HubService.java:48)
    //       at io.cody.all_relationships.rest.HubResource.getHub(HubResource.java:39)
    //   In order to prevent getHub(UUID)
    //   from throwing ResponseStatusException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   getHub(UUID).
    //   See https://diff.blue/R013 to resolve this issue.

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
    Hub hub = mock(Hub.class);
    when(hub.getMoFHubTX()).thenReturn(x4);
    when(hub.getOmFYTHub()).thenReturn(y1);
    when(hub.getManyExOne()).thenReturn("Many Ex One");
    when(hub.getMmFromHubToBBs()).thenReturn(new HashSet<>());
    when(hub.getId()).thenReturn(UUID.randomUUID());
    doNothing().when(hub).setDateCreated((OffsetDateTime) any());
    doNothing().when(hub).setId((UUID) any());
    doNothing().when(hub).setLastUpdated((OffsetDateTime) any());
    doNothing().when(hub).setManyExOne((String) any());
    doNothing().when(hub).setMmFromHubToBBs((java.util.Set<B>) any());
    doNothing().when(hub).setMoFHubTX((X) any());
    doNothing().when(hub).setOmFYTHub((Y) any());
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);
    HubRepository hubRepository = mock(HubRepository.class);
    when(hubRepository.findById((UUID) any())).thenReturn(Optional.empty());
    HubResource hubResource = new HubResource(
        new HubService(hubRepository, mock(XRepository.class), mock(YRepository.class),
            mock(BRepository.class)));
    hubResource.getHub(UUID.randomUUID());
  }

  /**
   * Method under test: {@link HubResource#getHub(UUID)}
   */
  @Test
  void testGetHub4() {
    //   Diffblue Cover was unable to write a Spring test,
    //   so wrote a non-Spring test instead.
    //   Reason: R015 Method may be nondeterministic.
    //   Diffblue Cover tried to run the created test twice, but it first passed the
    //   assertions and then failed.
    //   See https://diff.blue/R015 to resolve this issue.

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
    Hub hub = mock(Hub.class);
    when(hub.getMoFHubTX()).thenReturn(x4);
    when(hub.getOmFYTHub()).thenReturn(y1);
    when(hub.getManyExOne()).thenReturn("Many Ex One");
    when(hub.getMmFromHubToBBs()).thenReturn(new HashSet<>());
    when(hub.getId()).thenReturn(UUID.randomUUID());
    doNothing().when(hub).setDateCreated((OffsetDateTime) any());
    doNothing().when(hub).setId((UUID) any());
    doNothing().when(hub).setLastUpdated((OffsetDateTime) any());
    doNothing().when(hub).setManyExOne((String) any());
    doNothing().when(hub).setMmFromHubToBBs((java.util.Set<B>) any());
    doNothing().when(hub).setMoFHubTX((X) any());
    doNothing().when(hub).setOmFYTHub((Y) any());
    hub.setDateCreated(null);
    hub.setId(UUID.randomUUID());
    hub.setLastUpdated(null);
    hub.setManyExOne("Many Ex One");
    hub.setMmFromHubToBBs(new HashSet<>());
    hub.setMoFHubTX(x1);
    hub.setOmFYTHub(y);
    Optional.of(hub);

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    HubService hubService = mock(HubService.class);
    when(hubService.get((UUID) any())).thenReturn(hubDTO);
    HubResource hubResource = new HubResource(hubService);
    ResponseEntity<HubDTO> actualHub = hubResource.getHub(UUID.randomUUID());
    assertTrue(actualHub.hasBody());
    assertTrue(actualHub.getHeaders().isEmpty());
    assertEquals(HttpStatus.OK, actualHub.getStatusCode());
    verify(hub).setDateCreated((OffsetDateTime) any());
    verify(hub).setId((UUID) any());
    verify(hub).setLastUpdated((OffsetDateTime) any());
    verify(hub).setManyExOne((String) any());
    verify(hub).setMmFromHubToBBs((java.util.Set<B>) any());
    verify(hub).setMoFHubTX((X) any());
    verify(hub).setOmFYTHub((Y) any());
    verify(hubService).get((UUID) any());
  }

  /**
   * Method under test: {@link HubResource#deleteHub(UUID)}
   */
  @Test
  void testDeleteHub() throws Exception {
    doNothing().when(this.hubService).delete((UUID) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/hubs/{id}",
        UUID.randomUUID());
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.hubResource)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link HubResource#deleteHub(UUID)}
   */
  @Test
  void testDeleteHub2() throws Exception {
    doNothing().when(this.hubService).delete((UUID) any());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/hubs/{id}",
        UUID.randomUUID());
    deleteResult.contentType("https://example.org/example");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.hubResource).build()
        .perform(deleteResult);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link HubResource#getAllHubs()}
   */
  @Test
  void testGetAllHubs() throws Exception {
    when(this.hubService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/hubs");
    MockMvcBuilders.standaloneSetup(this.hubResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link HubResource#getAllHubs()}
   */
  @Test
  void testGetAllHubs2() throws Exception {
    when(this.hubService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/hubs");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.hubResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link HubResource#updateHub(UUID, HubDTO)}
   */
  @Test
  void testUpdateHub() throws Exception {
    doNothing().when(this.hubService).update((UUID) any(), (HubDTO) any());

    HubDTO hubDTO = new HubDTO();
    hubDTO.setId(UUID.randomUUID());
    hubDTO.setManyExOne("Many Ex One");
    hubDTO.setMmFromHubToBs(new ArrayList<>());
    hubDTO.setMoFHubTX(1L);
    hubDTO.setOmFYTHub(1L);
    String content = (new ObjectMapper()).writeValueAsString(hubDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/hubs/{id}",
            UUID.randomUUID())
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.hubResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

