package com.alquimiasoft.minegocio.mapper;

import com.alquimiasoft.minegocio.entity.ClientAddress;
import com.alquimiasoft.minegocio.entity.dto.AddressDto;

public class AddressMapper {

  public static ClientAddress dtoToInstance(AddressDto addressDto) {
    ClientAddress clientAddress = new ClientAddress();
    clientAddress.setProvince(addressDto.province());
    clientAddress.setCity(addressDto.city());
    clientAddress.setAddress(addressDto.address());
    clientAddress.setIsMatrix(false);

    return clientAddress;
  }

  public static AddressDto instanceToDto(ClientAddress clientAddress) {
    return new AddressDto(
        clientAddress.getId(),
        clientAddress.getProvince(),
        clientAddress.getCity(),
        clientAddress.getAddress(),
        clientAddress.getIsMatrix());
  }
}
