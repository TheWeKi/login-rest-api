package com.weki.loginrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class UserEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String username;
    private String email;
    private String password;

    @DBRef
    private List<Role> roles;
}
