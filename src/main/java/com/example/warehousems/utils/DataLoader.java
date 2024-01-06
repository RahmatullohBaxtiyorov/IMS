package com.example.warehousems.utils;

import com.example.warehousems.entity.Role;
import com.example.warehousems.entity.User;
import com.example.warehousems.enums.RoleName;
import com.example.warehousems.repository.RoleRepository;
import com.example.warehousems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Role role = new Role();
            role.setRoleName(RoleName.ROLE_ADMIN);
            roleRepository.save(role);

            Role role2 = new Role();
            role2.setRoleName(RoleName.ROLE_USER);
            roleRepository.save(role2);

            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRoleNames(Collections.singleton(role));
            user.setCode("1");
            userRepository.save(user);
        }


    }
}
