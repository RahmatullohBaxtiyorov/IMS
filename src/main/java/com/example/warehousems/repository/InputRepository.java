package com.example.warehousems.repository;

import com.example.warehousems.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InputRepository extends JpaRepository<Input, UUID> {
}