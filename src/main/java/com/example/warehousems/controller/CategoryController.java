package com.example.warehousems.controller;

import com.example.warehousems.payload.CategoryDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        ResponseApi responseApi = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(responseApi.isSuccess() ? 200 : 409).body(responseApi.getMessage());
    }

    @GetMapping
    public HttpEntity<?> getCategory() {
        return ResponseEntity.ok(categoryService.getCategory());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMeasurementById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCategory(@PathVariable UUID id, @RequestBody CategoryDto categoryDto) {
        ResponseApi responseApi = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(responseApi.isSuccess() ? 201 : 409).body(responseApi.getMessage());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable UUID id) {
        ResponseApi responseApi = categoryService.deleteCategory(id);
        return ResponseEntity.status(responseApi.isSuccess() ? 201 : 409).body(responseApi.getMessage());
    }


}
