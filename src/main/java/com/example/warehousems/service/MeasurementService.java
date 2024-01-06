package com.example.warehousems.service;

import com.example.warehousems.entity.Measurement;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public ResponseApi addMeasurement(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) return new ResponseApi("this name exists", false);
        measurementRepository.save(measurement);
        return new ResponseApi("Successfully added", true);
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(UUID id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }


    public ResponseApi deleteMeasurement(UUID id) {
        try {
            if (measurementRepository.findById(id).isPresent()) {
                measurementRepository.deleteById(id);
                return new ResponseApi("succsfully deleted", true);
            }
            return new ResponseApi("Xatolik", false);
        } catch (Exception e) {
            return new ResponseApi("Xatolik", false);
        }
    }

}

