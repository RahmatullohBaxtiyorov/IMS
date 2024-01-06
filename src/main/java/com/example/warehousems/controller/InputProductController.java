package com.example.warehousems.controller;

import com.example.warehousems.entity.InputProduct;
import com.example.warehousems.payload.InputProductDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.InputProductRepository;
import com.example.warehousems.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService productService;
    @Autowired
    InputProductRepository inputProductRepository;

    @PostMapping
    public HttpEntity<?> addIP(@RequestBody InputProductDto inputProductDto) {
        ResponseApi add = productService.add(inputProductDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add.getMessage());
    }

    @GetMapping
    public HttpEntity<?> getAllIP() {
        List<InputProduct> all = inputProductRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneInput(@PathVariable UUID id) {
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        return ResponseEntity.ok(byId.isPresent() ? byId.get() : ResponseEntity.badRequest());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInput(@PathVariable UUID id) {
        boolean present = inputProductRepository.findById(id).isPresent();
        if (present) {
            inputProductRepository.deleteById(id);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.badRequest().build();
    }


}
