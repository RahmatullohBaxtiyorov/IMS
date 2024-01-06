package com.example.warehousems.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String code;
    private String password;

}
