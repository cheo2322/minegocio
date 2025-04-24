package com.alquimiasoft.minegocio.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.helper.TestHelper;
import com.alquimiasoft.minegocio.repository.ClientRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

  @Mock ClientRepository clientRepository;

  @InjectMocks ClientServiceImpl clientService;

  private String findString;
  private String findParameter;

  @BeforeEach
  void setup() {
    findString = "12345";
    findParameter = "ID";
  }

  @Test
  void shouldReturnClients_whenFindIsDoneByIdentificationNumber() {
    // given
    when(clientRepository.findByIdentificationNumberContaining(findString))
        .thenReturn(List.of(TestHelper.buildClient()));

    // when
    List<ClientDto> clients = clientService.getClientsByFilter(findString, findParameter);

    // then
    assertFalse(clients.isEmpty());
    assertEquals(0L, clients.get(0).id());
    assertEquals("CEDULA", clients.get(0).identificationType());
    assertEquals("0000000000", clients.get(0).identificationNumber());
    assertEquals("Name", clients.get(0).name());
    assertEquals("x@y.z", clients.get(0).email());
    assertEquals("+593", clients.get(0).phoneNumber());
    assertEquals("Address", clients.get(0).mainAddress());
  }

  @Test
  void createClient() {}

  @Test
  void updateClient() {}

  @Test
  void deleteClient() {}

  @Test
  void createAddress() {}

  @Test
  void getAdditionalAddresses() {}
}
