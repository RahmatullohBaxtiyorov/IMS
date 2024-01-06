package com.example.warehousems.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class OutputProductDto {
    private UUID productId;
    private Double amount;
    private Double price;
    private UUID outputId;
}
