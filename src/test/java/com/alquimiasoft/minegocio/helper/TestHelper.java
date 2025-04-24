package com.alquimiasoft.minegocio.helper;

import com.alquimiasoft.minegocio.entity.Client;
import com.alquimiasoft.minegocio.entity.ClientAddress;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import java.util.List;

public class TestHelper {

  public static Client buildClient() {
    Client client = new Client();
    client.setId(0L);
    client.setIdentificationType(buildIdentificationType());
    client.setIdentificationNumber("0000000000");
    client.setName("Name");
    client.setEmail("x@y.z");
    client.setPhoneNumber("+593");
    client.setAddresses(List.of(buildClientAddress()));

    return client;
  }

  public static ClientDto buildClientDto() {
    return new ClientDto(
        0L, "CEDULA", "0000000000", "Name", "x@y.z", "+593", "Province", "City", "Address");
  }

  public static ClientDto buildClientDtoToUpdate() {
    return new ClientDto(null, null, null, "Name1", "a@b.c", "+5930", null, null, null);
  }

  public static ClientAddress buildClientAddress() {
    ClientAddress clientAddress = new ClientAddress();
    clientAddress.setId(2L);
    clientAddress.setProvince("Province");
    clientAddress.setCity("City");
    clientAddress.setAddress("Address");
    clientAddress.setIsMatrix(false);

    return clientAddress;
  }

  public static AddressDto buildAddressDto() {
    return new AddressDto(2L, "Province", "City", "Address", false);
  }

  private static IdentificationType buildIdentificationType() {
    IdentificationType identificationType = new IdentificationType();
    identificationType.setId(1L);
    identificationType.setIdentificationType("CEDULA");

    return identificationType;
  }
}
