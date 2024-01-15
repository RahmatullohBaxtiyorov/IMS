package com.example.warehousems.service;

import com.example.warehousems.entity.Currency;
import com.example.warehousems.entity.Input;
import com.example.warehousems.entity.Supplier;
import com.example.warehousems.entity.Warehouse;
import com.example.warehousems.payload.InputDto;
import com.example.warehousems.repository.CurrencyRepository;
import com.example.warehousems.repository.InputRepository;
import com.example.warehousems.repository.SupplierRepository;
import com.example.warehousems.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public ResponseEntity<?> createInput(InputDto inputDto) {

        Input input = new Input();
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalSupplier.isEmpty() || optionalWarehouse.isEmpty() || optionalCurrency.isEmpty())
            return ResponseEntity.badRequest().build();
        input.setCurrency(optionalCurrency.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCode(String.valueOf(Math.abs(new Random().nextInt())));
        input.setFactureNumber(String.valueOf(UUID.randomUUID()));
        inputRepository.save(input);
        return ResponseEntity.ok("success");


    }
}
