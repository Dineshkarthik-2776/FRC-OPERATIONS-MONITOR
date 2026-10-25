package com.jktyres.FRC.dto;

import com.jktyres.FRC.enums.Role;
import lombok.Getter;

@Getter
public class RegisterRequest {
    String name;
    String email;
    Role role;
    String password;
}
