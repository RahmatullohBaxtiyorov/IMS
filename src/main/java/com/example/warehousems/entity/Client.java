package com.example.warehousems.entity;

import com.example.warehousems.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data

public class Client extends AbsEntity {
}
