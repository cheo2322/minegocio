package com.alquimiasoft.minegocio.helper;

import com.alquimiasoft.minegocio.entity.Client;
import com.alquimiasoft.minegocio.entity.IdentificationType;

public class TestHelper {

  public static Client buildClient() {
    Client client = new Client();
    client.setId(0L);
    client.setIdentificationType(buildIdentificationType());
    client.setIdentificationNumber("0000000000");
    client.setName("Name");
    client.setEmail("x@y.z");
    client.setPhoneNumber("+593");

    return client;
  }

  private static IdentificationType buildIdentificationType() {
    IdentificationType identificationType = new IdentificationType();
    identificationType.setId(1L);
    identificationType.setIdentificationType("CEDULA");

    return identificationType;
  }
}
