package com.weki.loginrestapi.controller;

import com.weki.loginrestapi.dto.LoginDto;
import com.weki.loginrestapi.dto.RegisterDto;
import com.weki.loginrestapi.model.Role;
import com.weki.loginrestapi.model.UserEntity;
import com.weki.loginrestapi.repository.RoleRepository;
import com.weki.loginrestapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/authenticate")
    public String sayHello() {
        return "Hello, World";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if( userRepository.existsByUsername(registerDto.getUsername()) ) {
            return new ResponseEntity<>( "User Already Exists", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername( registerDto.getUsername() );
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()) );
        user.setEmail(registerDto.getEmail());

        Role role = roleRepository.findByRole("USER");

        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return new ResponseEntity<>("Registered", HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> register(@RequestBody LoginDto loginDto) {
        var token = new UsernamePasswordAuthenticationToken(
          loginDto.getUsername(), loginDto.getPassword()
        );
        Authentication authentication = authenticationManager
                .authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Logged In", HttpStatus.OK);
    }

}
