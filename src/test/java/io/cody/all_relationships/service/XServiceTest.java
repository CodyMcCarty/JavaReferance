package io.cody.all_relationships.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.cody.all_relationships.domain.Hub;
import io.cody.all_relationships.domain.X;
import io.cody.all_relationships.domain.Z;
import io.cody.all_relationships.model.Clearnance;
import io.cody.all_relationships.model.XDTO;
import io.cody.all_relationships.repos.XRepository;
import io.cody.all_relationships.repos.ZRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {XService.class})
@ExtendWith(SpringExtension.class)
class XServiceTest {

  @MockBean
  private XRepository xRepository;

  @Autowired
  private XService xService;

  @MockBean
  private ZRepository zRepository;

  /**
   * Method under test: {@link XService#findAll()}
   */
  @Test
  void testFindAll() {
    when(this.xRepository.findAll()).thenReturn(new ArrayList<>());
    assertTrue(this.xService.findAll().isEmpty());
    verify(this.xRepository).findAll();
  }

  /**
   * Method under test: {@link XService#findAll()}
   */
  @Test
  void testFindAll2() {
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

    ArrayList<X> xList = new ArrayList<>();
    xList.add(x1);
    when(this.xRepository.findAll()).thenReturn(xList);
    List<XDTO> actualFindAllResult = this.xService.findAll();
    assertEquals(1, actualFindAllResult.size());
    XDTO getResult = actualFindAllResult.get(0);
    assertEquals(Clearnance.VIEWER, getResult.getClearance());
    assertEquals(123L, getResult.getOoFXTZ().longValue());
    assertEquals(123L, getResult.getId().longValue());
    assertEquals("Has One To One", getResult.getHasOneToOne());
    assertEquals("Has Many To One Rel", getResult.getHasManyToOneRel());
    verify(this.xRepository).findAll();
  }

  /**
   * Method under test: {@link XService#findAll()}
   */
  @Test
  void testFindAll3() {
    when(this.xRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
    assertThrows(ResponseStatusException.class, () -> this.xService.findAll());
    verify(this.xRepository).findAll();
  }

  /**
   * Method under test: {@link XService#findAll()}
   */
  @Test
  void testFindAll4() {
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

    ArrayList<X> xList = new ArrayList<>();
    xList.add(x3);
    xList.add(x1);
    when(this.xRepository.findAll()).thenReturn(xList);
    List<XDTO> actualFindAllResult = this.xService.findAll();
    assertEquals(2, actualFindAllResult.size());
    XDTO getResult = actualFindAllResult.get(0);
    assertEquals(123L, getResult.getOoFXTZ().longValue());
    XDTO getResult1 = actualFindAllResult.get(1);
    assertEquals(123L, getResult1.getOoFXTZ().longValue());
    assertEquals(123L, getResult1.getId().longValue());
    assertEquals("Has One To One", getResult1.getHasOneToOne());
    assertEquals("Has Many To One Rel", getResult1.getHasManyToOneRel());
    assertEquals(Clearnance.VIEWER, getResult1.getClearance());
    assertEquals(123L, getResult.getId().longValue());
    assertEquals("Has One To One", getResult.getHasOneToOne());
    assertEquals("Has Many To One Rel", getResult.getHasManyToOneRel());
    assertEquals(Clearnance.VIEWER, getResult.getClearance());
    verify(this.xRepository).findAll();
  }

  /**
   * Method under test: {@link XService#get(Long)}
   */
  @Test
  void testGet() {
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
    XDTO actualGetResult = this.xService.get(123L);
    assertEquals(Clearnance.VIEWER, actualGetResult.getClearance());
    assertEquals(123L, actualGetResult.getOoFXTZ().longValue());
    assertEquals(123L, actualGetResult.getId().longValue());
    assertEquals("Has One To One", actualGetResult.getHasOneToOne());
    assertEquals("Has Many To One Rel", actualGetResult.getHasManyToOneRel());
    verify(this.xRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link XService#get(Long)}
   */
  @Test
  void testGet2() {
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

    Z z2 = new Z();
    z2.setDateCreated(null);
    z2.setHasOneToOne("Has One To One");
    z2.setId(123L);
    z2.setLastUpdated(null);
    z2.setOoFXTZ(new X());

    X x1 = new X();
    x1.setClearance(Clearnance.VIEWER);
    x1.setDateCreated(null);
    x1.setHasManyToOneRel("Has Many To One Rel");
    x1.setHasOneToOne("Has One To One");
    x1.setId(123L);
    x1.setLastUpdated(null);
    x1.setMoFHubTXHubs(new HashSet<>());
    x1.setOoFXTZ(z2);

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x1);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z3);

    Z z4 = new Z();
    z4.setDateCreated(null);
    z4.setHasOneToOne("Has One To One");
    z4.setId(123L);
    z4.setLastUpdated(null);
    z4.setOoFXTZ(x2);
    X x3 = mock(X.class);
    when(x3.getOoFXTZ()).thenReturn(z4);
    when(x3.getClearance()).thenReturn(Clearnance.VIEWER);
    when(x3.getId()).thenReturn(123L);
    when(x3.getHasManyToOneRel()).thenReturn("Has Many To One Rel");
    when(x3.getHasOneToOne()).thenReturn("Has One To One");
    doNothing().when(x3).setClearance((Clearnance) any());
    doNothing().when(x3).setDateCreated((OffsetDateTime) any());
    doNothing().when(x3).setHasManyToOneRel((String) any());
    doNothing().when(x3).setHasOneToOne((String) any());
    doNothing().when(x3).setId((Long) any());
    doNothing().when(x3).setLastUpdated((OffsetDateTime) any());
    doNothing().when(x3).setMoFHubTXHubs((java.util.Set<Hub>) any());
    doNothing().when(x3).setOoFXTZ((Z) any());
    x3.setClearance(Clearnance.VIEWER);
    x3.setDateCreated(null);
    x3.setHasManyToOneRel("Has Many To One Rel");
    x3.setHasOneToOne("Has One To One");
    x3.setId(123L);
    x3.setLastUpdated(null);
    x3.setMoFHubTXHubs(new HashSet<>());
    x3.setOoFXTZ(z1);
    assertThrows(ResponseStatusException.class, () -> this.xService.get(123L));
    verify(this.xRepository).findById((Long) any());
    verify(x3).setClearance((Clearnance) any());
    verify(x3).setDateCreated((OffsetDateTime) any());
    verify(x3).setHasManyToOneRel((String) any());
    verify(x3).setHasOneToOne((String) any());
    verify(x3).setId((Long) any());
    verify(x3).setLastUpdated((OffsetDateTime) any());
    verify(x3).setMoFHubTXHubs((java.util.Set<Hub>) any());
    verify(x3).setOoFXTZ((Z) any());
  }

  /**
   * Method under test: {@link XService#create(XDTO)}
   */
  @Test
  void testCreate() {
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

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);
    Optional<Z> ofResult = Optional.of(z1);
    when(this.zRepository.findById((Long) any())).thenReturn(ofResult);

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

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x3);

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);
    when(this.xRepository.save((X) any())).thenReturn(x4);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    assertEquals(123L, this.xService.create(xdto).longValue());
    verify(this.zRepository).findById((Long) any());
    verify(this.xRepository).save((X) any());
  }

  /**
   * Method under test: {@link XService#create(XDTO)}
   */
  @Test
  void testCreate2() {
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

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);
    Optional<Z> ofResult = Optional.of(z1);
    when(this.zRepository.findById((Long) any())).thenReturn(ofResult);
    when(this.xRepository.save((X) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    assertThrows(ResponseStatusException.class, () -> this.xService.create(xdto));
    verify(this.zRepository).findById((Long) any());
    verify(this.xRepository).save((X) any());
  }

  /**
   * Method under test: {@link XService#create(XDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreate3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "io.cody.all_relationships.repos.ZRepository.findById(Object)" is null
    //       at io.cody.all_relationships.service.XService.mapToEntity(XService.java:71)
    //       at io.cody.all_relationships.service.XService.create(XService.java:41)
    //   In order to prevent create(XDTO)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   create(XDTO).
    //   See https://diff.blue/R013 to resolve this issue.

    when(this.zRepository.findById((Long) any())).thenReturn(null);

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

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z1);
    when(this.xRepository.save((X) any())).thenReturn(x2);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    this.xService.create(xdto);
  }

  /**
   * Method under test: {@link XService#create(XDTO)}
   */
  @Test
  void testCreate4() {
    when(this.zRepository.findById((Long) any())).thenReturn(Optional.empty());

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

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);

    X x2 = new X();
    x2.setClearance(Clearnance.VIEWER);
    x2.setDateCreated(null);
    x2.setHasManyToOneRel("Has Many To One Rel");
    x2.setHasOneToOne("Has One To One");
    x2.setId(123L);
    x2.setLastUpdated(null);
    x2.setMoFHubTXHubs(new HashSet<>());
    x2.setOoFXTZ(z1);
    when(this.xRepository.save((X) any())).thenReturn(x2);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    assertThrows(ResponseStatusException.class, () -> this.xService.create(xdto));
    verify(this.zRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link XService#update(Long, XDTO)}
   */
  @Test
  void testUpdate() {
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

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);
    Optional<Z> ofResult = Optional.of(z1);
    when(this.zRepository.findById((Long) any())).thenReturn(ofResult);

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
    Optional<X> ofResult1 = Optional.of(x3);

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(new Z());

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

    Z z5 = new Z();
    z5.setDateCreated(null);
    z5.setHasOneToOne("Has One To One");
    z5.setId(123L);
    z5.setLastUpdated(null);
    z5.setOoFXTZ(x5);

    X x6 = new X();
    x6.setClearance(Clearnance.VIEWER);
    x6.setDateCreated(null);
    x6.setHasManyToOneRel("Has Many To One Rel");
    x6.setHasOneToOne("Has One To One");
    x6.setId(123L);
    x6.setLastUpdated(null);
    x6.setMoFHubTXHubs(new HashSet<>());
    x6.setOoFXTZ(z5);
    when(this.xRepository.save((X) any())).thenReturn(x6);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult1);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    this.xService.update(123L, xdto);
    verify(this.zRepository).findById((Long) any());
    verify(this.xRepository).save((X) any());
    verify(this.xRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link XService#update(Long, XDTO)}
   */
  @Test
  void testUpdate2() {
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

    Z z1 = new Z();
    z1.setDateCreated(null);
    z1.setHasOneToOne("Has One To One");
    z1.setId(123L);
    z1.setLastUpdated(null);
    z1.setOoFXTZ(x1);
    Optional<Z> ofResult = Optional.of(z1);
    when(this.zRepository.findById((Long) any())).thenReturn(ofResult);

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
    Optional<X> ofResult1 = Optional.of(x3);
    when(this.xRepository.save((X) any())).thenThrow(
        new ResponseStatusException(HttpStatus.CONTINUE));
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult1);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    assertThrows(ResponseStatusException.class, () -> this.xService.update(123L, xdto));
    verify(this.zRepository).findById((Long) any());
    verify(this.xRepository).save((X) any());
    verify(this.xRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link XService#update(Long, XDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testUpdate3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "io.cody.all_relationships.repos.ZRepository.findById(Object)" is null
    //       at io.cody.all_relationships.service.XService.mapToEntity(XService.java:71)
    //       at io.cody.all_relationships.service.XService.update(XService.java:48)
    //   In order to prevent update(Long, XDTO)
    //   from throwing NullPointerException, add constructors or factory
    //   methods that make it easier to construct fully initialized objects used in
    //   update(Long, XDTO).
    //   See https://diff.blue/R013 to resolve this issue.

    when(this.zRepository.findById((Long) any())).thenReturn(null);

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

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x3);

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);
    when(this.xRepository.save((X) any())).thenReturn(x4);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    this.xService.update(123L, xdto);
  }

  /**
   * Method under test: {@link XService#update(Long, XDTO)}
   */
  @Test
  void testUpdate4() {
    when(this.zRepository.findById((Long) any())).thenReturn(Optional.empty());

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

    Z z3 = new Z();
    z3.setDateCreated(null);
    z3.setHasOneToOne("Has One To One");
    z3.setId(123L);
    z3.setLastUpdated(null);
    z3.setOoFXTZ(x3);

    X x4 = new X();
    x4.setClearance(Clearnance.VIEWER);
    x4.setDateCreated(null);
    x4.setHasManyToOneRel("Has Many To One Rel");
    x4.setHasOneToOne("Has One To One");
    x4.setId(123L);
    x4.setLastUpdated(null);
    x4.setMoFHubTXHubs(new HashSet<>());
    x4.setOoFXTZ(z3);
    when(this.xRepository.save((X) any())).thenReturn(x4);
    when(this.xRepository.findById((Long) any())).thenReturn(ofResult);

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    assertThrows(ResponseStatusException.class, () -> this.xService.update(123L, xdto));
    verify(this.zRepository).findById((Long) any());
    verify(this.xRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link XService#delete(Long)}
   */
  @Test
  void testDelete() {
    doNothing().when(this.xRepository).deleteById((Long) any());
    this.xService.delete(123L);
    verify(this.xRepository).deleteById((Long) any());
  }

  /**
   * Method under test: {@link XService#delete(Long)}
   */
  @Test
  void testDelete2() {
    doThrow(new ResponseStatusException(HttpStatus.CONTINUE)).when(this.xRepository)
        .deleteById((Long) any());
    assertThrows(ResponseStatusException.class, () -> this.xService.delete(123L));
    verify(this.xRepository).deleteById((Long) any());
  }
}

