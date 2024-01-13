package com.example.warehousems.controller;

import com.example.warehousems.entity.Input;
import com.example.warehousems.payload.InputDto;
import com.example.warehousems.repository.InputRepository;
import com.example.warehousems.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/input")
@CrossOrigin
public class InputController {


    @Autowired
    InputService inputService;
    @Autowired
    InputRepository inputRepository;

    @PostMapping
    public HttpEntity<?> addInput(@RequestBody InputDto inputDto) {
        return inputService.createInput(inputDto);
    }

    @GetMapping
    public HttpEntity<?> getAllInput() {
        List<Input> all = inputRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneInput(@PathVariable UUID id) {
        Optional<Input> byId = inputRepository.findById(id);
        return ResponseEntity.ok(byId.isPresent() ? byId.get() : ResponseEntity.badRequest());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInput(@PathVariable UUID id) {
        boolean present = inputRepository.findById(id).isPresent();
        if (present) {
            inputRepository.deleteById(id);
            return ResponseEntity.ok("success");
        }
        ;
        return ResponseEntity.badRequest().build();
    }


}
