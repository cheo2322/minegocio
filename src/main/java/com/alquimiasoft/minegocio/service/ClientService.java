package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import java.util.List;

/** Service for CRUD and any operation related to Client entity. */
public interface ClientService {

  /**
   * Get Client by using identification or name.
   *
   * @param findString string to find the Client.
   * @return a list with all matches.
   */
  List<ClientDto> getClientsByFilter(String findString, String findParameter);

  /**
   * Create a new Client.
   *
   * @param clientDto with information for the new Client.
   * @return the new Client information.
   */
  ClientDto createClient(ClientDto clientDto);

  /**
   * Update the Client information.
   *
   * @param id to find the Client.
   * @param clientDto with data to be updated.
   * @return the updated Client DTO.
   */
  ClientDto updateClient(Long id, ClientDto clientDto);

  /**
   * Delete a Client by turning it inactive.
   *
   * @param id to find the Client.
   */
  void deleteClient(Long id);

  /**
   * Add a new address to a Client.
   *
   * @param clientId to find the Client.
   * @param addressDto with the information of the new Address.
   * @return the new Address DTO.
   */
  AddressDto createAddress(Long clientId, AddressDto addressDto);

  /**
   * List all additional Address from a Client.
   *
   * @param clientId to find the Client.
   * @return a list of Address DTO.
   */
  List<AddressDto> getAdditionalAddresses(Long clientId);
}
