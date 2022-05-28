package io.cody.all_relationships.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cody.all_relationships.model.NeedsAddressDTO;
import io.cody.all_relationships.service.NeedsAddressService;
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

@ContextConfiguration(classes = {NeedsAddressResource.class})
@ExtendWith(SpringExtension.class)
class NeedsAddressResourceTest {

  @Autowired
  private NeedsAddressResource needsAddressResource;

  @MockBean
  private NeedsAddressService needsAddressService;

  /**
   * Method under test: {@link NeedsAddressResource#createNeedsAddress(NeedsAddressDTO)}
   */
  @Test
  void testCreateNeedsAddress() throws Exception {
    when(this.needsAddressService.findAll()).thenReturn(new ArrayList<>());

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    String content = (new ObjectMapper()).writeValueAsString(needsAddressDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/needsAddresss")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NeedsAddressResource#deleteNeedsAddress(Long)}
   */
  @Test
  void testDeleteNeedsAddress() throws Exception {
    doNothing().when(this.needsAddressService).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
        "/api/needsAddresss/{id}", 123L);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link NeedsAddressResource#deleteNeedsAddress(Long)}
   */
  @Test
  void testDeleteNeedsAddress2() throws Exception {
    doNothing().when(this.needsAddressService).delete((Long) any());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
        "/api/needsAddresss/{id}", 123L);
    deleteResult.contentType("https://example.org/example");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(deleteResult);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link NeedsAddressResource#getAllNeedsAddresss()}
   */
  @Test
  void testGetAllNeedsAddresss() throws Exception {
    when(this.needsAddressService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/needsAddresss");
    MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NeedsAddressResource#getAllNeedsAddresss()}
   */
  @Test
  void testGetAllNeedsAddresss2() throws Exception {
    when(this.needsAddressService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/needsAddresss");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NeedsAddressResource#getNeedsAddress(Long)}
   */
  @Test
  void testGetNeedsAddress() throws Exception {
    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    when(this.needsAddressService.get((Long) any())).thenReturn(needsAddressDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/api/needsAddresss/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"id\":123,\"needsAddress\":\"42 Main St\"}"));
  }

  /**
   * Method under test: {@link NeedsAddressResource#getNeedsAddress(Long)}
   */
  @Test
  void testGetNeedsAddress2() throws Exception {
    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    when(this.needsAddressService.get((Long) any())).thenReturn(needsAddressDTO);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/needsAddresss/{id}",
        123L);
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"id\":123,\"needsAddress\":\"42 Main St\"}"));
  }

  /**
   * Method under test: {@link NeedsAddressResource#updateNeedsAddress(Long, NeedsAddressDTO)}
   */
  @Test
  void testUpdateNeedsAddress() throws Exception {
    doNothing().when(this.needsAddressService).update((Long) any(), (NeedsAddressDTO) any());

    NeedsAddressDTO needsAddressDTO = new NeedsAddressDTO();
    needsAddressDTO.setId(123L);
    needsAddressDTO.setNeedsAddress("42 Main St");
    String content = (new ObjectMapper()).writeValueAsString(needsAddressDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(
            "/api/needsAddresss/{id}", 123L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.needsAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

