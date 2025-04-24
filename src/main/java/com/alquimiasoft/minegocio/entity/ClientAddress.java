package com.alquimiasoft.minegocio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

  @Column(nullable = false, name = "is_matrix")
  private Boolean isMatrix;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;
}
