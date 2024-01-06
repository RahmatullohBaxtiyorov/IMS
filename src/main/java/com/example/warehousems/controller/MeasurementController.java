package com.example.warehousems.controller;

import com.example.warehousems.entity.Measurement;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public HttpEntity<?> addMeasurement(@RequestBody Measurement measurement) {
        ResponseApi responseApi = measurementService.addMeasurement(measurement);
        return ResponseEntity.status(responseApi.isSuccess() ? 201 : 409).body(responseApi.getMessage());
    }

    @GetMapping
    public HttpEntity<?> getMeasurement() {
       return ResponseEntity.ok(measurementService.getMeasurements());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMeasurementById(@PathVariable UUID id) {
        return ResponseEntity.ok(measurementService.getMeasurementById(id));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMeasurement(@PathVariable UUID id){
        ResponseApi responseApi = measurementService.deleteMeasurement(id);
        return ResponseEntity.status(responseApi.isSuccess() ? 201 : 409).body(responseApi.getMessage());
    }


}
