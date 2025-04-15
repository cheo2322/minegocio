package com.alquimiasoft.minegocio.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.repository.ClientRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

  @Mock ClientRepository clientRepository;

  @InjectMocks ClientServiceImpl clientService;

  @Test
  void getClientsByFilter() {
    // given
    String findString = "12345";

    // when
    List<ClientDto> clients = clientService.getClientsByFilter(findString);

    // then
    assertTrue(clients.isEmpty());
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
