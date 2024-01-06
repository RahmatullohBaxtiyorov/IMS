package com.example.warehousems.controller;

import com.example.warehousems.entity.Warehouse;
import com.example.warehousems.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping
    public HttpEntity<?> getWarehouses() {
        return ResponseEntity.ok(warehouseRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getWarehouseById(@PathVariable UUID id) {
        return ResponseEntity.ok(warehouseRepository.findById(id));
    }

    @PostMapping
    public HttpEntity<?> addWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse newwarehouse = new Warehouse();
        newwarehouse.setName(warehouse.getName());
        return ResponseEntity.ok(warehouseRepository.save(newwarehouse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWarehouse(@PathVariable UUID id, @RequestBody Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) return (ResponseEntity<?>) ResponseEntity.badRequest();
        Warehouse newwarehouse = new Warehouse();
        newwarehouse.setName(warehouse.getName());
        return ResponseEntity.ok(warehouseRepository.save(newwarehouse));
    }
}
