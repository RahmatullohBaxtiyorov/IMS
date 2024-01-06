package com.example.warehousems.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class OutputProduct {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private Double amount;

    private Double price;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Output output;

}
