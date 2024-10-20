package com.codigo.jose.inga.examen.controller;

import com.codigo.jose.inga.examen.entity.OrderEntity;
import com.codigo.jose.inga.examen.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/v1")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<OrderEntity>> createOrders(@RequestBody List<OrderEntity> orderEntities){
        List<OrderEntity> orders = orderService.createOrders(orderEntities);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<List<OrderEntity>> getAllOrders(@PathVariable String status){
        List<OrderEntity> orders = orderService.getAllOrders(status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id){
        OrderEntity order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderEntity){
        OrderEntity order = orderService.updateOrder(id, orderEntity);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
