package com.example.warehousems.repository;

import com.example.warehousems.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
}