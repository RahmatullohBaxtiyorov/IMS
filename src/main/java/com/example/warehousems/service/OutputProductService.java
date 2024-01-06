package com.example.warehousems.service;

import com.example.warehousems.entity.Output;
import com.example.warehousems.entity.OutputProduct;
import com.example.warehousems.entity.Product;
import com.example.warehousems.payload.OutputProductDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.OutputProductRepository;
import com.example.warehousems.repository.OutputRepository;
import com.example.warehousems.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public ResponseApi addOP(OutputProductDto productDto) {
        OutputProduct outputProduct = new OutputProduct();
        Optional<Product> optionalProduct = productRepository.findById(productDto.getProductId());
        optionalProduct.ifPresent(outputProduct::setProduct);
        Optional<Output> optionalOutput = outputRepository.findById(productDto.getOutputId());
        optionalOutput.ifPresent(outputProduct::setOutput);
        outputProduct.setAmount(productDto.getAmount());
        outputProduct.setPrice(productDto.getPrice());
        outputProductRepository.save(outputProduct);

        return new ResponseApi("sucess", true);
    }
}
