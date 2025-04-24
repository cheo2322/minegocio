package com.alquimiasoft.minegocio.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alquimiasoft.minegocio.entity.Client;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.helper.TestHelper;
import com.alquimiasoft.minegocio.repository.ClientRepository;
import com.alquimiasoft.minegocio.repository.IdentificationTypeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

  @Mock ClientRepository clientRepository;

  @Mock IdentificationTypeRepository identificationTypeRepository;

  @InjectMocks ClientServiceImpl clientService;

  @Test
  void shouldReturnClients_whenFindIsDoneByIdentificationNumber() {
    // given
    String findString = "12345";
    String findParameter = "ID";

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
  void shouldReturnClients_whenFindIsDoneByName() {
    // given
    String findString = "am";
    String findParameter = "NAME";

    when(clientRepository.findByNameContaining(findString))
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
  void shouldThrowBadRequest_whenFindParameterIsNotAllowed() {
    // given
    String findString = "am";
    String findParameter = "NAME1";

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(
            IllegalArgumentException.class,
            () -> clientService.getClientsByFilter(findString, findParameter));

    // then
    assertEquals(
        "No enum constant com.alquimiasoft.minegocio.service.enums.FindParameter.NAME1",
        illegalArgumentException.getMessage());
  }

  @Test
  void shouldReturnClientDto_whenCreatesANewClient() {
    // given
    ClientDto clientDto = TestHelper.buildClientDto();
    Client client = TestHelper.buildClient();

    when(clientRepository.findByIdentificationNumber(anyString())).thenReturn(Optional.empty());
    when(identificationTypeRepository.findByIdentificationType(anyString()))
        .thenReturn(Optional.of(client.getIdentificationType()));
    when(clientRepository.save(any(Client.class))).thenReturn(client);

    // when
    ClientDto clientResponse = clientService.createClient(clientDto);

    // then
    assertNotNull(clientResponse);
    assertEquals(0L, clientResponse.id());
    assertEquals("CEDULA", clientResponse.identificationType());
    assertEquals("0000000000", clientResponse.identificationNumber());
    assertEquals("Name", clientResponse.name());
    assertEquals("x@y.z", clientResponse.email());
    assertEquals("+593", clientResponse.phoneNumber());
    assertEquals("Address", clientResponse.mainAddress());
  }

  @Test
  void shouldThrowIllegalArgumentException_whenIdentificationTypeIsNotFound_inCreateClient() {
    // given
    ClientDto clientDto = TestHelper.buildClientDto();

    when(clientRepository.findByIdentificationNumber(anyString())).thenReturn(Optional.empty());
    when(identificationTypeRepository.findByIdentificationType(anyString()))
        .thenReturn(Optional.empty());

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(IllegalArgumentException.class, () -> clientService.createClient(clientDto));

    // then
    assertEquals("ID type not found.", illegalArgumentException.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentException_whenClientAlreadyExist_inCreateClient() {
    // given
    ClientDto clientDto = TestHelper.buildClientDto();
    Client client = TestHelper.buildClient();

    when(clientRepository.findByIdentificationNumber(anyString())).thenReturn(Optional.of(client));

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(IllegalArgumentException.class, () -> clientService.createClient(clientDto));

    // then
    assertEquals("Client already exists.", illegalArgumentException.getMessage());
  }

  @Test
  void updateClient() {}

  @Test
  void deleteClient() {}

  @Test
  void createAddress() {}

  @Test
  void getAdditionalAddresses() {}
}
