package com.example.warehousems.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {

    private String name;
    private UUID categoryId;
    private UUID photoId;
    private UUID measurementId;



}
