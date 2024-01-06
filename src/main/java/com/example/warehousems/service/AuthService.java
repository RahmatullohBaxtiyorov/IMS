package com.example.warehousems.service;

import com.example.warehousems.config.JwtService;
import com.example.warehousems.entity.User;
import com.example.warehousems.enums.RoleName;
import com.example.warehousems.payload.LoginDto;
import com.example.warehousems.payload.RegisterDto;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.repository.RoleRepository;
import com.example.warehousems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseApi registerUser(RegisterDto registerDto) {
        Optional<User> email = userRepository.findByEmail(registerDto.getEmail());
        if (email.isPresent()) return new ResponseApi("this email already exists", false);

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoleNames(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_USER)));
        user.setCode(String.valueOf(new Random().nextInt()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new ResponseApi("successfull", true, jwtToken);

    }

    public ResponseApi loginUser(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new ResponseApi("successfull", true, jwtToken);

    }
}
