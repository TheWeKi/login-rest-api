package com.weki.loginrestapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "Username must not be Blank")
    private String username;

    @NotBlank(message = "Password must not be Blank")
    private String password;
}
