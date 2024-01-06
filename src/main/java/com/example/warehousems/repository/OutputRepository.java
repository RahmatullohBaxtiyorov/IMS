package com.example.warehousems.repository;

import com.example.warehousems.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutputRepository extends JpaRepository<Output, UUID> {
}