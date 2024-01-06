package com.example.warehousems.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class InputDto {

    private UUID warehouseId;
    private UUID supplierId;
    private UUID currencyId;
    private String factureNumber;
    private String code;

}
