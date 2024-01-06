package com.example.warehousems.controller;

import com.example.warehousems.payload.LoginDto;
import com.example.warehousems.payload.RegisterDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ResponseApi responseApi = authService.registerUser(registerDto);
        return ResponseEntity.status(responseApi.isSuccess() ? 201 : 409).body(responseApi.getMessage() + "  token  "+ responseApi.getObject());
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        ResponseApi responseApi = authService.loginUser(loginDto);
        return ResponseEntity.status(responseApi.isSuccess() ? 201 : 409).body(responseApi.getMessage() + "  token  "+ responseApi.getObject());
    }

}
