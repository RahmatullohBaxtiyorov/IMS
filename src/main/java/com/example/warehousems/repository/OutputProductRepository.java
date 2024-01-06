package com.example.warehousems.repository;

import com.example.warehousems.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutputProductRepository extends JpaRepository<OutputProduct, UUID> {
}