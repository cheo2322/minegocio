package com.alquimiasoft.minegocio.entity.dto;

public record ClientDto(
    Long id,
    String identificationType,
    String identificationNumber,
    String name,
    String email,
    String phoneNumber,
    String mainAddress) {}
