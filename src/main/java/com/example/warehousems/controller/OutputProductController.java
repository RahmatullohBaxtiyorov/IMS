package com.example.warehousems.controller;

import com.example.warehousems.entity.OutputProduct;
import com.example.warehousems.payload.OutputProductDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.OutputProductRepository;
import com.example.warehousems.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/outputproduct")
@CrossOrigin
public class OutputProductController {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public ResponseEntity<?> addOP(@RequestBody OutputProductDto productDto) {
        ResponseApi responseApi = outputProductService.addOP(productDto);
        return ResponseEntity.ok(responseApi);
    }

    @GetMapping
    public ResponseEntity<List<OutputProduct>> getOP() {
        List<OutputProduct> all = outputProductRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputProduct> getOPOne(@PathVariable UUID id) {
        Optional<OutputProduct> all = outputProductRepository.findById(id);
        return all.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        outputProductRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
