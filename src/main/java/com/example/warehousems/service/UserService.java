package com.example.warehousems.service;

import com.example.warehousems.entity.User;
import com.example.warehousems.entity.Warehouse;
import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.payload.UserDto;
import com.example.warehousems.repository.UserRepository;
import com.example.warehousems.repository.WarehouseRepository;
import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

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

    public ResponseApi editUserN(UUID id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDto.getWarehousesId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Warehouse id1 = optionalWarehouse.get();
            user.setWarehouses(Stream.of(id1).collect(Collectors.toSet()));
            userRepository.save(user);
        }
        return null;
    }
}
