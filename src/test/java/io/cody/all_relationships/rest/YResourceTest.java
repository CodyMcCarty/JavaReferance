package io.cody.all_relationships.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cody.all_relationships.model.YDTO;
import io.cody.all_relationships.service.YService;
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

@ContextConfiguration(classes = {YResource.class})
@ExtendWith(SpringExtension.class)
class YResourceTest {

  @Autowired
  private YResource yResource;

  @MockBean
  private YService yService;

  /**
   * Method under test: {@link YResource#createY(YDTO)}
   */
  @Test
  void testCreateY() throws Exception {
    when(this.yService.findAll()).thenReturn(new ArrayList<>());

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    String content = (new ObjectMapper()).writeValueAsString(ydto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/ys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.yResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link YResource#deleteY(Long)}
   */
  @Test
  void testDeleteY() throws Exception {
    doNothing().when(this.yService).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/ys/{id}",
        123L);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.yResource).build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link YResource#deleteY(Long)}
   */
  @Test
  void testDeleteY2() throws Exception {
    doNothing().when(this.yService).delete((Long) any());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/ys/{id}",
        123L);
    deleteResult.contentType("https://example.org/example");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.yResource).build()
        .perform(deleteResult);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link YResource#getAllYs()}
   */
  @Test
  void testGetAllYs() throws Exception {
    when(this.yService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/ys");
    MockMvcBuilders.standaloneSetup(this.yResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link YResource#getAllYs()}
   */
  @Test
  void testGetAllYs2() throws Exception {
    when(this.yService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/ys");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.yResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link YResource#getY(Long)}
   */
  @Test
  void testGetY() throws Exception {
    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    when(this.yService.get((Long) any())).thenReturn(ydto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/ys/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.yResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"id\":123,\"hasOneToManyRel\":\"Has One To Many Rel\"}"));
  }

  /**
   * Method under test: {@link YResource#getY(Long)}
   */
  @Test
  void testGetY2() throws Exception {
    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    when(this.yService.get((Long) any())).thenReturn(ydto);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/ys/{id}", 123L);
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.yResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"id\":123,\"hasOneToManyRel\":\"Has One To Many Rel\"}"));
  }

  /**
   * Method under test: {@link YResource#updateY(Long, YDTO)}
   */
  @Test
  void testUpdateY() throws Exception {
    doNothing().when(this.yService).update((Long) any(), (YDTO) any());

    YDTO ydto = new YDTO();
    ydto.setHasOneToManyRel("Has One To Many Rel");
    ydto.setId(123L);
    String content = (new ObjectMapper()).writeValueAsString(ydto);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/ys/{id}", 123L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.yResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

