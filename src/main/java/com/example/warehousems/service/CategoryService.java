package com.example.warehousems.service;

import com.example.warehousems.entity.Category;
import com.example.warehousems.payload.CategoryDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseApi addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentId());
            if (optionalCategory.isEmpty())
                return new ResponseApi("this parent category not exist", false);
            category.setParentCategory(optionalCategory.get());

        }
        categoryRepository.save(category);
        return new ResponseApi("successfull", true);
    }

    public List<Category> getCategory() {

        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ResponseApi editCategory(UUID id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) return new ResponseApi("category not exist", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentId() != null) {
            Optional<Category> optionalCategory2 = categoryRepository.findById(categoryDto.getParentId());
            if (optionalCategory2.isEmpty())
                return new ResponseApi("this parent category not exist", false);
            category.setParentCategory(optionalCategory2.get());

        }
        categoryRepository.save(category);
        return new ResponseApi("successful", true);


    }

    public ResponseApi deleteCategory(UUID id) {
        try {
            if (categoryRepository.findById(id).isPresent()) {
                categoryRepository.deleteById(id);
                return new ResponseApi("succsfully deleted", true);
            }
            return new ResponseApi("Xatolik", false);
        } catch (Exception e) {
            return new ResponseApi("Xatolik", false);
        }
    }
}

