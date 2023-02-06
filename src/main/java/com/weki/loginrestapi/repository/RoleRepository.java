package com.weki.loginrestapi.repository;

import com.weki.loginrestapi.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {
    Role findByRole(String role);
}
