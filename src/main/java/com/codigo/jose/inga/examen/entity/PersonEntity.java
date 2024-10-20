package com.codigo.jose.inga.examen.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @Column(name = "status", nullable = false)
    private String status;

    // ClientEntity only create or update a AddressEntity.
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressEntity address;

    // ClientEntity can create, update, delete, refresh or detach a OrderEntity.
    @JsonManagedReference
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}
