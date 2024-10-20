package com.codigo.jose.inga.examen.service.impl;

import com.codigo.jose.inga.examen.entity.OrderEntity;
import com.codigo.jose.inga.examen.exception.OrderNotFoundException;
import com.codigo.jose.inga.examen.exception.PersonNotFoundException;
import com.codigo.jose.inga.examen.repository.OrderRepository;
import com.codigo.jose.inga.examen.repository.PersonRepository;
import com.codigo.jose.inga.examen.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<OrderEntity> createOrders(List<OrderEntity> orderEntities) {
        // Validations
        orderEntities.forEach(order -> {
            // Verify if OrderEntities has a person
            if (order.getPerson() == null || order.getPerson().getId() == null) throw new PersonNotFoundException("person: { id } -> object person with id is required");
            // Verify if Person exists
            personRepository.findById(order.getPerson().getId()).orElseThrow(() -> new PersonNotFoundException("Person with id: " + order.getPerson().getId() + " not found"));
        });

        // Assign the status PENDING to all orders
        orderEntities.forEach(orderEntity -> orderEntity.setStatus("PENDING"));

        // Create the orders
        return orderRepository.saveAll(orderEntities);
    }

    @Override
    public List<OrderEntity> getAllOrders(String status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found"));
    }

    @Override
    public OrderEntity updateOrder(Long id, OrderEntity orderEntity) {
        // Verify if the order exists to update
        OrderEntity order = getOrderById(id);

        // Verify if OrderEntity has a person
        if (orderEntity.getPerson() == null || orderEntity.getPerson().getId() == null) throw new PersonNotFoundException("person: { id } -> object person with id is required");

        // Verify if Person exists
        personRepository.findById(orderEntity.getPerson().getId()).orElseThrow(() -> new PersonNotFoundException("Person with id: " + orderEntity.getPerson().getId() + " not found"));

        order.setDescription(orderEntity.getDescription());
        order.setQuantity(orderEntity.getQuantity());

        // Update the status of the order
        order.setStatus("PROCESSED");

        // Assign the person to the order
        order.setPerson(orderEntity.getPerson());

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        OrderEntity order = getOrderById(id);

        order.setStatus("DELETED");

        orderRepository.save(order);
    }
}
