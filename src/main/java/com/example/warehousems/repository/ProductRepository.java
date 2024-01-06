package com.example.warehousems.repository;

import com.example.warehousems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByNameAndCategoryId(String name, UUID category_id);
}