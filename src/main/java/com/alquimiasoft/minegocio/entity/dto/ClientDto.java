package com.alquimiasoft.minegocio.entity.dto;

import java.util.List;

public record ClientDto(
    Long id,
    String identificationType,
    String identificationNumber,
    String name,
    String email,
    String phoneNumber,
    List<String> addresses) {}
