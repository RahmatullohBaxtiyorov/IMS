package com.example.warehousems.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class InputProduct {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private Double amount;

    private Double price;

    private Date expireDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Input input;

}
