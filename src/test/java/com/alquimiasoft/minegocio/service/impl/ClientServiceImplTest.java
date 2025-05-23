package com.alquimiasoft.minegocio.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alquimiasoft.minegocio.entity.Client;
import com.alquimiasoft.minegocio.entity.ClientAddress;
import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.helper.TestHelper;
import com.alquimiasoft.minegocio.repository.ClientAddressRepository;
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

  @Mock ClientAddressRepository clientAddressRepository;

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
  void shouldReturnClientDto_whenAClientIsUpdated() {
    // given
    Client client = TestHelper.buildClient();
    ClientDto clientDtoUpdate = TestHelper.buildClientDtoToUpdate();

    when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
    when(clientRepository.save(any(Client.class))).thenReturn(client);

    // when
    ClientDto response = clientService.updateClient(0L, clientDtoUpdate);

    // then
    assertNotNull(response);
    assertEquals("Name1", response.name());
    assertEquals("a@b.c", response.email());
    assertEquals("+5930", response.phoneNumber());
  }

  @Test
  void shouldThrowIllegalArgumentException_whenClientDoesNotExist_inUpdateClient() {
    // given
    ClientDto clientDtoUpdate = TestHelper.buildClientDtoToUpdate();

    when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(
            IllegalArgumentException.class, () -> clientService.updateClient(0L, clientDtoUpdate));

    // then
    assertNotNull(illegalArgumentException);
    assertEquals("Client does not exist.", illegalArgumentException.getMessage());
  }

  @Test
  void shouldDeleteAClientByID() {
    // given
    Client client = TestHelper.buildClient();

    when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
    doNothing().when(clientRepository).deleteById(anyLong());

    // when
    clientService.deleteClient(0L);

    // then
    verify(clientRepository).findById(0L);
    verify(clientRepository).deleteById(0L);
  }

  @Test
  void shouldThrowIllegalArgumentException_whenClientIsNotFound_inDeleteClient() {
    // given
    when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(IllegalArgumentException.class, () -> clientService.deleteClient(0L));

    // then
    assertNotNull(illegalArgumentException);
    assertEquals("Client does not exist.", illegalArgumentException.getMessage());

    verify(clientRepository).findById(0L);
  }

  @Test
  void shouldReturnAddress_whenAddedANewOneToAClient() {
    // given
    Client client = TestHelper.buildClient();
    ClientAddress clientAddress = TestHelper.buildClientAddress();
    AddressDto addressDto = TestHelper.buildAddressDto();

    when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
    when(clientAddressRepository.save(any(ClientAddress.class))).thenReturn(clientAddress);

    // when
    AddressDto response = clientService.createAddress(0L, addressDto);

    // then
    assertNotNull(response);
    assertEquals(2L, response.id());
    assertEquals("Province", response.province());
    assertEquals("City", response.city());
    assertEquals("Address", response.address());
    assertFalse(response.isMatrix());
  }

  @Test
  void shouldThrowIllegalArgumentException_whenClientIsNotFound_inCreateAddress() {
    // given
    AddressDto addressDto = TestHelper.buildAddressDto();

    when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(
            IllegalArgumentException.class, () -> clientService.createAddress(0L, addressDto));

    // then
    assertNotNull(illegalArgumentException);
    assertEquals("Client does not exist.", illegalArgumentException.getMessage());

    verify(clientRepository).findById(0L);
  }

  @Test
  void shouldReturnAddresses() {
    // given
    Client client = TestHelper.buildClient();

    when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

    // when
    List<AddressDto> addresses = clientService.getAddresses(0L);

    // then
    assertNotNull(addresses);
    assertEquals(1, addresses.size());
    assertEquals(2L, addresses.get(0).id());
    assertEquals("Province", addresses.get(0).province());
    assertEquals("City", addresses.get(0).city());
    assertEquals("Address", addresses.get(0).address());
    assertFalse(addresses.get(0).isMatrix());
  }

  @Test
  void shouldThrowIllegalArgumentException_whenClientIsNotFound_inGetAddresses() {
    // given
    when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    IllegalArgumentException illegalArgumentException =
        assertThrows(IllegalArgumentException.class, () -> clientService.getAddresses(0L));

    // then
    assertNotNull(illegalArgumentException);
    assertEquals("Client does not exist.", illegalArgumentException.getMessage());

    verify(clientRepository).findById(0L);
  }
}
