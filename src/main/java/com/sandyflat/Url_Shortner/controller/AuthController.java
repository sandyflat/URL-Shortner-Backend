package com.sandyflat.Url_Shortner.controller;

import com.sandyflat.Url_Shortner.dto.LoginRequest;
import com.sandyflat.Url_Shortner.dto.RegisterRequest;
import com.sandyflat.Url_Shortner.dto.RegisterResponse;
import com.sandyflat.Url_Shortner.model.User;
import com.sandyflat.Url_Shortner.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> resisterUser(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.
               status(HttpStatus.CREATED).
               body(userService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }
}
