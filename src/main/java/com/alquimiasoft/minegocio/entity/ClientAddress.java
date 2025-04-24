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
  private String address;

  @Column(nullable = false, name = "is_matrix")
  private Boolean isMatrix;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;
}
