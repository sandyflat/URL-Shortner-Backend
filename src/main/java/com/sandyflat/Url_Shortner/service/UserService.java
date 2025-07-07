package com.sandyflat.Url_Shortner.service;

import com.sandyflat.Url_Shortner.dto.LoginRequest;
import com.sandyflat.Url_Shortner.dto.RegisterRequest;
import com.sandyflat.Url_Shortner.dto.RegisterResponse;
import com.sandyflat.Url_Shortner.model.User;
import com.sandyflat.Url_Shortner.repository.UserRepository;
import com.sandyflat.Url_Shortner.security.JwtAuthenticationResponse;
import com.sandyflat.Url_Shortner.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public RegisterResponse registerUser(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("ROLE_USER");
        user.setEmail(registerRequest.getEmail());
        User createdUser = userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setUsername(createdUser.getUsername());
        response.setEmail(createdUser.getEmail());
        response.setRole(createdUser.getRole());

        return response;
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        return new JwtAuthenticationResponse(jwt);
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username: " + name)
                );
    }
}
