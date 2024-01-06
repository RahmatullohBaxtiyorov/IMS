package com.example.warehousems.controller;

import com.example.warehousems.entity.Product;
import com.example.warehousems.payload.ProductDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        ResponseApi responseApi = productService.addProduct(productDto);
        return ResponseEntity.status(responseApi.isSuccess() ? 200 : 409).body(responseApi.getMessage());
    }

    @GetMapping
    public HttpEntity<?> getProduct() {
        List<Product> product = productService.getProduct();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneProduct(@PathVariable UUID id) {
        Product oneProduct = productService.getOneProduct(id);
        return ResponseEntity.ok(oneProduct);
    }

}
