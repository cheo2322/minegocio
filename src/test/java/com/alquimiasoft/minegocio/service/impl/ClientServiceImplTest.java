package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {

  @Mock ClientRepository clientRepository;

  @InjectMocks ClientServiceImpl clientService;

  @Test
  void getClientsByFilter() {}

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
