package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.entity.Client;
import com.alquimiasoft.minegocio.entity.ClientAddress;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.mapper.ClientMapper;
import com.alquimiasoft.minegocio.repository.ClientRepository;
import com.alquimiasoft.minegocio.repository.IdentificationTypeRepository;
import com.alquimiasoft.minegocio.service.ClientService;
import com.alquimiasoft.minegocio.service.enums.FindParameter;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private final IdentificationTypeRepository identificationTypeRepository;

  public ClientServiceImpl(
      ClientRepository clientRepository,
      IdentificationTypeRepository identificationTypeRepository) {
    this.clientRepository = clientRepository;
    this.identificationTypeRepository = identificationTypeRepository;
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
    Optional<Client> clientDB =
        clientRepository.findByIdentificationNumber(clientDto.identificationNumber());

    if (clientDB.isPresent()) {
      throw new IllegalArgumentException("Client already exists.");
    }

    Optional<IdentificationType> identificationType =
        identificationTypeRepository.findByIdentificationType(clientDto.identificationType());

    if (identificationType.isEmpty()) {
      throw new IllegalArgumentException("ID type not found.");
    }

    Client client = ClientMapper.dtoToInstance(clientDto);
    client.setIdentificationType(identificationType.get());

    ClientAddress clientAddress = new ClientAddress();
    clientAddress.setAddress(clientDto.mainAddress());
    clientAddress.setIsMatrix(true);
    clientAddress.setClient(client);

    client.setAddresses(List.of(clientAddress));

    return ClientMapper.instanceToDto(clientRepository.save(client));
  }

  @Override
  public ClientDto updateClient(Long id, ClientDto clientDto) {
    Optional<Client> clientDB = clientRepository.findById(id);

    if (clientDB.isEmpty()) {
      throw new IllegalArgumentException("Client does not exist.");
    }

    Client client = clientDB.get();

    if (!StringUtils.isBlank(clientDto.name())) {
      client.setName(clientDto.name());
    }

    if (!StringUtils.isBlank(clientDto.email())) {
      client.setEmail(clientDto.email());
    }

    if (!StringUtils.isBlank(clientDto.phoneNumber())) {
      client.setPhoneNumber(clientDto.phoneNumber());
    }

    return ClientMapper.instanceToDto(clientRepository.save(client));
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
