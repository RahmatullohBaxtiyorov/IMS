package com.example.warehousems.payload;

import com.example.warehousems.enums.RoleName;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String code;
    private String password;
    private RoleName roleName;
    private UUID warehousesId;
}
