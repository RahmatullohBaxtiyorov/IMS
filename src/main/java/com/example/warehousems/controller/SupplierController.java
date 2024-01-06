package com.example.warehousems.controller;

import com.example.warehousems.entity.Supplier;
import com.example.warehousems.payload.SupplierDto;
import com.example.warehousems.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/supplier")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping
    public HttpEntity<?> addSupplier(@RequestBody SupplierDto supplierDto) {
        boolean b = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (b) return ResponseEntity.badRequest().build();
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return ResponseEntity.ok("success");
    }

    @GetMapping
    public HttpEntity<List<Supplier>> getSuppliers() {
        return ResponseEntity.ok(supplierRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Supplier> getSuppliersOne(@PathVariable UUID id) {
        if (supplierRepository.findById(id).isPresent())
            return ResponseEntity.ok(supplierRepository.findById(id).get());

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editSupplier(@PathVariable UUID id, @RequestBody SupplierDto supplierDto) {
        if (supplierRepository.findById(id).isPresent()) {
            Supplier supplier = new Supplier();
            supplier.setName(supplierDto.getName());
            supplier.setPhoneNumber(supplierDto.getPhoneNumber());
            supplierRepository.save(supplier);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSupplier(@PathVariable UUID id) {
        if (supplierRepository.findById(id).isPresent()) supplierRepository.deleteById(id);
        return ResponseEntity.badRequest().build();
    }

}
