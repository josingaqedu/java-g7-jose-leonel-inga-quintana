package com.codigo.jose.inga.examen.repository;

import com.codigo.jose.inga.examen.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findAllByStatus(String status); // status: ACTIVE | INACTIVE
    Optional<PersonEntity> findByDni(String dni);
}
