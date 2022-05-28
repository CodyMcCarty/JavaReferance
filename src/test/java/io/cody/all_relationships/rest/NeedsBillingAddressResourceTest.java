package io.cody.all_relationships.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cody.all_relationships.model.NeedsBillingAddressDTO;
import io.cody.all_relationships.service.NeedsBillingAddressService;
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

@ContextConfiguration(classes = {NeedsBillingAddressResource.class})
@ExtendWith(SpringExtension.class)
class NeedsBillingAddressResourceTest {

  @Autowired
  private NeedsBillingAddressResource needsBillingAddressResource;

  @MockBean
  private NeedsBillingAddressService needsBillingAddressService;

  /**
   * Method under test: {@link NeedsBillingAddressResource#createNeedsBillingAddress(NeedsBillingAddressDTO)}
   */
  @Test
  void testCreateNeedsBillingAddress() throws Exception {
    when(this.needsBillingAddressService.findAll()).thenReturn(new ArrayList<>());

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    String content = (new ObjectMapper()).writeValueAsString(needsBillingAddressDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "/api/needsBillingAddresss")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.needsBillingAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#deleteNeedsBillingAddress(Long)}
   */
  @Test
  void testDeleteNeedsBillingAddress() throws Exception {
    doNothing().when(this.needsBillingAddressService).delete((Long) any());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
        "/api/needsBillingAddresss/{id}",
        123L);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(
            this.needsBillingAddressResource)
        .build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#deleteNeedsBillingAddress(Long)}
   */
  @Test
  void testDeleteNeedsBillingAddress2() throws Exception {
    doNothing().when(this.needsBillingAddressService).delete((Long) any());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
        "/api/needsBillingAddresss/{id}", 123L);
    deleteResult.contentType("https://example.org/example");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(
            this.needsBillingAddressResource)
        .build()
        .perform(deleteResult);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#getAllNeedsBillingAddresss()}
   */
  @Test
  void testGetAllNeedsBillingAddresss() throws Exception {
    when(this.needsBillingAddressService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/api/needsBillingAddresss");
    MockMvcBuilders.standaloneSetup(this.needsBillingAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#getAllNeedsBillingAddresss()}
   */
  @Test
  void testGetAllNeedsBillingAddresss2() throws Exception {
    when(this.needsBillingAddressService.findAll()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get(
        "/api/needsBillingAddresss");
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.needsBillingAddressResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#getNeedsBillingAddress(Long)}
   */
  @Test
  void testGetNeedsBillingAddress() throws Exception {
    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    when(this.needsBillingAddressService.get((Long) any())).thenReturn(needsBillingAddressDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/api/needsBillingAddresss/{id}", 123L);
    MockMvcBuilders.standaloneSetup(this.needsBillingAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"id\":123,\"needsBillingAddress\":\"42 Main St\"}"));
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#getNeedsBillingAddress(Long)}
   */
  @Test
  void testGetNeedsBillingAddress2() throws Exception {
    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    when(this.needsBillingAddressService.get((Long) any())).thenReturn(needsBillingAddressDTO);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get(
        "/api/needsBillingAddresss/{id}", 123L);
    getResult.contentType("https://example.org/example");
    MockMvcBuilders.standaloneSetup(this.needsBillingAddressResource)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"id\":123,\"needsBillingAddress\":\"42 Main St\"}"));
  }

  /**
   * Method under test: {@link NeedsBillingAddressResource#updateNeedsBillingAddress(Long,
   * NeedsBillingAddressDTO)}
   */
  @Test
  void testUpdateNeedsBillingAddress() throws Exception {
    doNothing().when(this.needsBillingAddressService)
        .update((Long) any(), (NeedsBillingAddressDTO) any());

    NeedsBillingAddressDTO needsBillingAddressDTO = new NeedsBillingAddressDTO();
    needsBillingAddressDTO.setId(123L);
    needsBillingAddressDTO.setNeedsBillingAddress("42 Main St");
    String content = (new ObjectMapper()).writeValueAsString(needsBillingAddressDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(
            "/api/needsBillingAddresss/{id}", 123L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(this.needsBillingAddressResource)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

