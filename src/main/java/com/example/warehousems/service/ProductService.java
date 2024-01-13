package com.example.warehousems.service;

import com.example.warehousems.entity.*;
import com.example.warehousems.payload.ProductDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public ResponseApi addProduct(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists) return new ResponseApi("this product already exits in this category", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) return new ResponseApi("this category not exists", false);


//---------------------bu 2 qator kod qachon deploy qilinganda yoqib qo'yilsin---------------------------
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
//        if (optionalAttachment.isEmpty()) return new ResponseApi("this photo not exists", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()) return new ResponseApi("this measurement not exists", false);


        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setCode(String.valueOf(new Random().nextInt()));
//        product.setPhoto(optionalAttachment.get()); // bu kod ishlashi uchun fayl yuklanishi kerak
        product.setPhoto(null);
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new ResponseApi("saved", true);
    }

    public List<Product> getProduct() {

        return productRepository.findAll();

    }

    public Product getOneProduct(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        boolean present = optionalProduct.isPresent();
        if (present) return optionalProduct.get();
        return null;
    }
}
