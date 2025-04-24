package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.service.ClientService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alquimiasoft/minegocio/v1/clients")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/{findString}")
  @ResponseStatus(HttpStatus.OK)
  public List<ClientDto> getClients(
      @PathVariable String findString, @RequestParam String findParameter) {
    return clientService.getClientsByFilter(findString, findParameter);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClientDto createClient(@RequestBody ClientDto clientDto) {
    return clientService.createClient(clientDto);
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
    return clientService.updateClient(id, clientDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteClient(@PathVariable Long id) {
    clientService.deleteClient(id);
  }

  @PostMapping("/{clientId}/address")
  @ResponseStatus(HttpStatus.CREATED)
  public AddressDto createAddress(@PathVariable Long clientId, @RequestBody AddressDto addressDto) {
    return clientService.createAddress(clientId, addressDto);
  }

  @GetMapping("/{clientId}/mainAddress")
  @ResponseStatus(HttpStatus.OK)
  public List<AddressDto> getAdditionalAddresses(@PathVariable Long clientId) {
    return clientService.getAdditionalAddresses(clientId);
  }
}
