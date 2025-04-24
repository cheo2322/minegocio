package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.mapper.ClientMapper;
import com.alquimiasoft.minegocio.repository.ClientRepository;
import com.alquimiasoft.minegocio.service.ClientService;
import com.alquimiasoft.minegocio.service.enums.FindParameter;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public List<ClientDto> getClientsByFilter(String findString, String findParameter) {
    switch (FindParameter.valueOf(findParameter)) {
      case ID -> {
        return clientRepository.findByIdentificationNumberContaining(findString).stream()
            .map(ClientMapper::instanceToDto)
            .toList();
      }
      case NAME -> {
        return clientRepository.findByNameContaining(findString).stream()
            .map(ClientMapper::instanceToDto)
            .toList();
      }
    }

    return List.of();
  }

  @Override
  public ClientDto createClient(ClientDto clientDto) {
    return null;
  }

  @Override
  public ClientDto updateClient(Long id, ClientDto clientDto) {
    return null;
  }

  @Override
  public ClientDto deleteClient(Long id) {
    return null;
  }

  @Override
  public AddressDto createAddress(Long clientId, AddressDto addressDto) {
    return null;
  }

  @Override
  public List<AddressDto> getAdditionalAddresses(Long clientId) {
    return List.of();
  }
}
