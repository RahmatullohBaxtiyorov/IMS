package com.example.warehousems.controller;


import com.example.warehousems.entity.Currency;
import com.example.warehousems.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping
    public HttpEntity<?> getCurrencys() {
        return ResponseEntity.ok(currencyRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getcurrencyById(@PathVariable UUID id) {
        return ResponseEntity.ok(currencyRepository.findById(id));
    }

    @PostMapping
    public HttpEntity<?> addCurrency(@RequestBody Currency currency) {
        Currency newcurrency = new Currency();
        newcurrency.setName(currency.getName());
        return ResponseEntity.ok(currencyRepository.save(newcurrency));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCurrency(@PathVariable UUID id, @RequestBody Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()) return (ResponseEntity<?>) ResponseEntity.badRequest();
        Currency newcurrency = new Currency();
        newcurrency.setName(currency.getName());
        return ResponseEntity.ok(currencyRepository.save(newcurrency));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable UUID id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()) return (ResponseEntity<?>) ResponseEntity.badRequest();
        currencyRepository.deleteById(id);
        return ResponseEntity.noContent().build();


    }
}
