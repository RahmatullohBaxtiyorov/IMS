package com.example.warehousems.service;

import com.example.warehousems.entity.Input;
import com.example.warehousems.entity.InputProduct;
import com.example.warehousems.entity.Product;
import com.example.warehousems.payload.InputProductDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.InputProductRepository;
import com.example.warehousems.repository.InputRepository;
import com.example.warehousems.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    public ResponseApi add(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isPresent() && optionalProduct.isPresent()) {
            InputProduct inputProduct = new InputProduct();
            inputProduct.setProduct(optionalProduct.get());
            inputProduct.setInput(optionalInput.get());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setExpireDate(Date.from(LocalDate.now().plusDays(3).plusMonths(3).plusYears(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            inputProductRepository.save(inputProduct);
            return new ResponseApi("success", true);
        }
        return new ResponseApi("failed", false);
    }
}
