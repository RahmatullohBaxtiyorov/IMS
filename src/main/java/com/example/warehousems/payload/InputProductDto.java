package com.example.warehousems.payload;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class InputProductDto {

    private UUID productId;
    private Double amount;
    private Double price;
    private Date expireDate;
    private UUID inputId;

}
