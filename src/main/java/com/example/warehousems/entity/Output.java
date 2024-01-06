package com.example.warehousems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Currency currency;

    private String factureNumber;

    @Column(unique = true, nullable = false)
    private String code;

}
