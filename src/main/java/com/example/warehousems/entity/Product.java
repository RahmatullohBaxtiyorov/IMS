package com.example.warehousems.entity;

import com.example.warehousems.entity.template.AbsEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbsEntity {
    @ManyToOne(optional = false)
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment photo;
    private String code;
    @ManyToOne(optional = false)
    private Measurement measurement;

}
