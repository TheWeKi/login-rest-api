package com.weki.loginrestapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDto {
    @Size(message = "Size of Username Not in Range(4-16)", min = 4, max = 16)
    @NotBlank(message = "Username must not be Blank")
    private String username;

    @Email(message = "Email must be valid email")
    @NotBlank(message = "Email must not be Blank")
    private String email;

    @Size(message = "Size of Password Not in Range(4-32)", min = 4, max = 32)
    @NotBlank(message = "Password must not be Blank")
    private String password;
}
