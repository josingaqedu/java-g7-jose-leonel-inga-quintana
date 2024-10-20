package com.codigo.jose.inga.examen.repository;

import com.codigo.jose.inga.examen.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByStatus(String status); // status: PENDING | PROCESSED | CONFIRMED | DELETED
}
