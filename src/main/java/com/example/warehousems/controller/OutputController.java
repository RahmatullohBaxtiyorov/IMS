package com.example.warehousems.controller;

import com.example.warehousems.entity.Output;
import com.example.warehousems.payload.OutputDto;
import com.example.warehousems.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping
    public ResponseEntity<?> getOutputs() {
        List<Output> output = outputService.getOutputs();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOutput(@PathVariable UUID id) {
        Output output = outputService.getOutput(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity<?> addOutput(@RequestBody OutputDto outputDto) {
        return outputService.addOutput(outputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editOutput(@PathVariable UUID id, @RequestBody OutputDto outputDto) {
        return outputService.editOutput(id  , outputDto);
    }


}
