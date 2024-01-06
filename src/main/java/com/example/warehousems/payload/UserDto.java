package com.example.warehousems.payload;

import com.example.warehousems.enums.RoleName;
import lombok.Data;

@Data

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String code;
    private String password;
    private RoleName roleName;
}
