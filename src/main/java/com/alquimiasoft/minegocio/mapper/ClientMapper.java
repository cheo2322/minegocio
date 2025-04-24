package com.alquimiasoft.minegocio.mapper;

import com.alquimiasoft.minegocio.entity.Client;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;

public class ClientMapper {

  public static ClientDto instanceToDto(Client client) {
    return new ClientDto(
        client.getId(),
        client.getIdentificationType().getIdentificationType(),
        client.getIdentificationNumber(),
        client.getName(),
        client.getEmail(),
        client.getPhoneNumber(),
        client.getAddresses().get(0).getAddress());
  }

  public static Client dtoToInstance(ClientDto clientDto) {
    Client client = new Client();
    client.setIdentificationNumber(clientDto.identificationNumber());
    client.setName(clientDto.name());
    client.setEmail(clientDto.email());
    client.setPhoneNumber(clientDto.phoneNumber());

    return client;
  }
}
