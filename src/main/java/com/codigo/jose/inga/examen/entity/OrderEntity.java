package com.codigo.jose.inga.examen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false)
    private String status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private PersonEntity person;
}
