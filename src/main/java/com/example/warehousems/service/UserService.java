package com.example.warehousems.service;

import com.example.warehousems.entity.User;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.payload.UserDto;
import com.example.warehousems.repository.UserRepository;
import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseApi editUser(UUID id, Map<Object, Object> fields) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {


            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, optionalUser.get(), value);
            });
            userRepository.save(optionalUser.get());
            return new ResponseApi("success", true);
        }
        return null;
    }
}
