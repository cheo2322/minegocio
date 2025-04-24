package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.entity.dto.AddressDto;
import com.alquimiasoft.minegocio.entity.dto.ClientDto;
import com.alquimiasoft.minegocio.service.ClientService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
    return clientService.updateClient(id, clientDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClientDto deleteClient(@PathVariable Long id) {
    return clientService.deleteClient(id);
  }

  @PostMapping("/{clientId}/mainAddress")
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
