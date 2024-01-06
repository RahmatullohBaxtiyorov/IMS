package com.example.warehousems.service;

import com.example.warehousems.entity.Client;
import com.example.warehousems.entity.Currency;
import com.example.warehousems.entity.Output;
import com.example.warehousems.entity.Warehouse;
import com.example.warehousems.payload.OutputDto;
import com.example.warehousems.repository.ClientRepository;
import com.example.warehousems.repository.CurrencyRepository;
import com.example.warehousems.repository.OutputRepository;
import com.example.warehousems.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Output> getOutputs() {
        return outputRepository.findAll();
    }

    public Output getOutput(UUID id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        boolean empty = optionalOutput.isEmpty();
        if (empty) return null;
        return optionalOutput.get();
    }

    public ResponseEntity<?> addOutput(OutputDto outputDto) {
        Output output = new Output();
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());

        if (optionalWarehouse.isEmpty() || optionalClient.isEmpty() || optionalCurrency.isEmpty())
            return ResponseEntity.badRequest().build();
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setCode(outputDto.getCode());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return ResponseEntity.ok().build();


    }

    public ResponseEntity<?> editOutput(UUID id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) return addOutput(outputDto);
        return ResponseEntity.badRequest().build();

    }
}
