package com.weki.loginrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Base64Token {

    private String username;
    private String password;

    public String getToken() {
        String value = this.username+":"+this.password;
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

}
