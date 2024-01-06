package com.example.warehousems.controller;

import com.example.warehousems.entity.User;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.UserRepository;
import com.example.warehousems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUsers(@PathVariable UUID id) {
        Optional<User> all = userRepository.findById(id);
        if (all.isPresent())
            return ResponseEntity.ok(all.get());
        else
            return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> editUser(@PathVariable UUID id, Map<Object, Object> fields) {
        ResponseApi responseApi = userService.editUser(id, fields);
        return ResponseEntity.ok(responseApi);
    }

}
