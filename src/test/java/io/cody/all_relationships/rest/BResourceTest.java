package io.cody.all_relationships.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cody.all_relationships.model.BDTO;
import io.cody.all_relationships.service.BService;
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

@ContextConfiguration(classes = {BResource.class})
@ExtendWith(SpringExtension.class)
class BResourceTest {

  @Autowired
  private BResource bResource;

  @MockBean
  private BService bService;

  /**
   * Method under test: {@link BResource#createB(BDTO)}
   */
  @Test
  void testCreateB() throws Exception {
    when(this.bService.findAll()).thenReturn(new ArrayList<>());

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    String content = (new ObjectMapper()).writeValueAsString(bdto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.bResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link BResource#deleteB(Long)}
   */
  @Test
  void testDeleteB() throws Exception {
    doNothing().when(this.bService).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/bs/{id}",
        123L);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.bResource).build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link BResource#deleteB(Long)}
   */
  @Test
  void testDeleteB2() throws Exception {
    doNothing().when(this.bService).delete((Long) any());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/bs/{id}",
        123L);
    deleteResult.contentType("https://example.org/example");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.bResource).build()
        .perform(deleteResult);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link BResource#getAllBs()}
   */
  @Test
  void testGetAllBs() throws Exception {
    when(this.bService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bs");
    MockMvcBuilders.standaloneSetup(this.bResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link BResource#getAllBs()}
   */
  @Test
  void testGetAllBs2() throws Exception {
    when(this.bService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/bs");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.bResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link BResource#getB(Long)}
   */
  @Test
  void testGetB() throws Exception {
    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    when(this.bService.get((Long) any())).thenReturn(bdto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bs/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.bResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"id\":123,\"manyExTwo\":\"Many Ex Two\"}"));
  }

  /**
   * Method under test: {@link BResource#getB(Long)}
   */
  @Test
  void testGetB2() throws Exception {
    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    when(this.bService.get((Long) any())).thenReturn(bdto);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/bs/{id}", 123L);
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.bResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"id\":123,\"manyExTwo\":\"Many Ex Two\"}"));
  }

  /**
   * Method under test: {@link BResource#updateB(Long, BDTO)}
   */
  @Test
  void testUpdateB() throws Exception {
    doNothing().when(this.bService).update((Long) any(), (BDTO) any());

    BDTO bdto = new BDTO();
    bdto.setId(123L);
    bdto.setManyExTwo("Many Ex Two");
    String content = (new ObjectMapper()).writeValueAsString(bdto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/bs/{id}", 123L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.bResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

