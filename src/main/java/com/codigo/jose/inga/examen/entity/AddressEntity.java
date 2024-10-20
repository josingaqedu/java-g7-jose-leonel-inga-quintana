package com.codigo.jose.inga.examen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avenue", nullable = false)
    private String avenue;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "department", nullable = false)
    private String department;
}
