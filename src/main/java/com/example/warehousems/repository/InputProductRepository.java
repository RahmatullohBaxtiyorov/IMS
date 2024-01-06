package com.example.warehousems.repository;

import com.example.warehousems.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InputProductRepository extends JpaRepository<InputProduct, UUID> {
}