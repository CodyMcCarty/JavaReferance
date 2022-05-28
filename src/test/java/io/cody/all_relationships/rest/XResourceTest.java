package io.cody.all_relationships.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cody.all_relationships.model.Clearnance;
import io.cody.all_relationships.model.XDTO;
import io.cody.all_relationships.service.XService;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {XResource.class})
@ExtendWith(SpringExtension.class)
class XResourceTest {

  @Autowired
  private XResource xResource;

  @MockBean
  private XService xService;

  /**
   * Method under test: {@link XResource#createX(XDTO)}
   */
  @Test
  void testCreateX() throws Exception {
    when(this.xService.findAll()).thenReturn(new ArrayList<>());

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    String content = (new ObjectMapper()).writeValueAsString(xdto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/xs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.xResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link XResource#deleteX(Long)}
   */
  @Test
  void testDeleteX() throws Exception {
    doNothing().when(this.xService).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/xs/{id}",
        123L);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.xResource).build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link XResource#deleteX(Long)}
   */
  @Test
  void testDeleteX2() throws Exception {
    doNothing().when(this.xService).delete((Long) any());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/xs/{id}",
        123L);
    deleteResult.contentType("https://example.org/example");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.xResource).build()
        .perform(deleteResult);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link XResource#getAllXs()}
   */
  @Test
  void testGetAllXs() throws Exception {
    when(this.xService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/xs");
    MockMvcBuilders.standaloneSetup(this.xResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link XResource#getAllXs()}
   */
  @Test
  void testGetAllXs2() throws Exception {
    when(this.xService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/xs");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.xResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link XResource#getX(Long)}
   */
  @Test
  void testGetX() throws Exception {
    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    when(this.xService.get((Long) any())).thenReturn(xdto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/xs/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.xResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":123,\"hasManyToOneRel\":\"Has Many To One Rel\",\"hasOneToOne\":\"Has One To One\",\"clearance\":\"VIEWER"
                    + "\",\"ooFXTZ\":1}"));
  }

  /**
   * Method under test: {@link XResource#getX(Long)}
   */
  @Test
  void testGetX2() throws Exception {
    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    when(this.xService.get((Long) any())).thenReturn(xdto);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/xs/{id}", 123L);
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.xResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":123,\"hasManyToOneRel\":\"Has Many To One Rel\",\"hasOneToOne\":\"Has One To One\",\"clearance\":\"VIEWER"
                    + "\",\"ooFXTZ\":1}"));
  }

  /**
   * Method under test: {@link XResource#updateX(Long, XDTO)}
   */
  @Test
  void testUpdateX() throws Exception {
    doNothing().when(this.xService).update((Long) any(), (XDTO) any());

    XDTO xdto = new XDTO();
    xdto.setClearance(Clearnance.VIEWER);
    xdto.setHasManyToOneRel("Has Many To One Rel");
    xdto.setHasOneToOne("Has One To One");
    xdto.setId(123L);
    xdto.setOoFXTZ(1L);
    String content = (new ObjectMapper()).writeValueAsString(xdto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/xs/{id}", 123L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.xResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

