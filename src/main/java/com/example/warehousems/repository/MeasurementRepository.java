package com.example.warehousems.repository;

import com.example.warehousems.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {
    boolean existsByName(String name);
}