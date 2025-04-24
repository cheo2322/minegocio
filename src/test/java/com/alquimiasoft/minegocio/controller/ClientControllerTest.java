package com.alquimiasoft.minegocio.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.helper.TestHelper;
import com.alquimiasoft.minegocio.service.impl.ClientServiceImpl;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

  @Autowired WebApplicationContext webApplicationContext;

  MockMvc mockMvc;

  @MockitoBean ClientServiceImpl clientService;

  @SneakyThrows
  @Test
  void testGetClients() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    List<ClientDto> mockClients = List.of(TestHelper.buildClientDto());

    when(clientService.getClientsByFilter("am", "NAME")).thenReturn(mockClients);

    mockMvc
        .perform(get("/alquimiasoft/minegocio/v1/clients/am").param("findParameter", "NAME"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Name"));
  }

  @SneakyThrows
  @Test
  void testCreateClient() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    ClientDto mockClient = TestHelper.buildClientDto();

    when(clientService.createClient(any(ClientDto.class))).thenReturn(mockClient);

    mockMvc
        .perform(
            post("/alquimiasoft/minegocio/v1/clients")
                .contentType("application/json")
                .content(
                    """
                        {
                          "identificationType": "CEDULA",
                          "identificationNumber": "0000000000",
                          "name": "Name",
                          "email": "x@y.z",
                          "phoneNumber": "+593",
                          "mainProvince": "Province",
                          "mainCity": "City",
                          "mainAddress": "Address"
                        }
                        """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Name"));
  }

  @SneakyThrows
  @Test
  void testUpdateClient() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    ClientDto updatedClient = TestHelper.buildClientDtoToUpdate();

    when(clientService.updateClient(eq(0L), any(ClientDto.class))).thenReturn(updatedClient);

    mockMvc
        .perform(
            patch("/alquimiasoft/minegocio/v1/clients/0")
                .contentType("application/json")
                .content(
                    """
                        {
                          "name": "Name1",
                          "email": "a@b.c",
                          "phoneNumber": "+5930"
                        }
                        """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Name1"))
        .andExpect(jsonPath("$.email").value("a@b.c"))
        .andExpect(jsonPath("$.phoneNumber").value("+5930"));
  }

  @SneakyThrows
  @Test
  void testDeleteClient() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    doNothing().when(clientService).deleteClient(0L);

    mockMvc.perform(delete("/alquimiasoft/minegocio/v1/clients/0")).andExpect(status().isOk());

    verify(clientService, times(1)).deleteClient(0L);
  }

  @Test
  void testCreateAddress() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    AddressDto mockAddress = TestHelper.buildAddressDto();

    when(clientService.createAddress(eq(0L), any(AddressDto.class))).thenReturn(mockAddress);

    mockMvc
        .perform(
            post("/alquimiasoft/minegocio/v1/clients/0/addresses")
                .contentType("application/json")
                .content(
                    """
                        {
                          "province": "Province1",
                          "city": "City1",
                          "address": "Address1"
                        }
                        """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.province").value("Province"))
        .andExpect(jsonPath("$.city").value("City"))
        .andExpect(jsonPath("$.address").value("Address"));
  }

  @Test
  void testGetAdditionalAddresses() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    List<AddressDto> mockAddresses = List.of(TestHelper.buildAddressDto());

    when(clientService.getAddresses(0L)).thenReturn(mockAddresses);

    mockMvc
        .perform(get("/alquimiasoft/minegocio/v1/clients/0/addresses"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].address").value("Address"));
  }
}
