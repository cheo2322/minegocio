package com.alquimiasoft.minegocio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client_addresses")
@Getter
@Setter
public class ClientAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String province;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String address;

  @ManyToOne
  @JoinColumn(name = "id")
  private Client client;
}
