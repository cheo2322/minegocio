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
}
