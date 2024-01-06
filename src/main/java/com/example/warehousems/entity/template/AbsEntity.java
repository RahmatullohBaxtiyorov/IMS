package com.example.warehousems.entity.template;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbsEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private boolean active = true;

}
