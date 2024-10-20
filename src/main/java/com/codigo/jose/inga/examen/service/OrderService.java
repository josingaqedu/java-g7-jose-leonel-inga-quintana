package com.codigo.jose.inga.examen.service;

import com.codigo.jose.inga.examen.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> createOrders(List<OrderEntity> orderEntities);
    List<OrderEntity> getAllOrders(String status);
    OrderEntity getOrderById(Long id);
    OrderEntity updateOrder(Long id, OrderEntity orderEntity);
    void deleteOrder(Long id);
}
