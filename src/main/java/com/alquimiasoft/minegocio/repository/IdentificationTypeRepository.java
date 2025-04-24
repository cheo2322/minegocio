package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.IdentificationType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificationTypeRepository extends JpaRepository<IdentificationType, Long> {

  Optional<IdentificationType> findByIdentificationType(String identificationType);
}
