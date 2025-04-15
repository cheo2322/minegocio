package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
