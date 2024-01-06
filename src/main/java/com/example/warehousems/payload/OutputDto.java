package com.example.warehousems.payload;


import lombok.Data;

import java.util.UUID;

@Data
public class OutputDto {

    private UUID warehouseId;

    private UUID clientId;

    private UUID currencyId;

    private String factureNumber;

    private String code;
}
