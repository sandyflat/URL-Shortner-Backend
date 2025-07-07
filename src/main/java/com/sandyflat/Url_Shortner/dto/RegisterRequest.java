package com.sandyflat.Url_Shortner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username shouldn't be blanked")
    private String username;

    @Email(message = "Email address is not valid")
    private String email;

    private String role;

    @NotBlank(message = "Password should be entered")
    private String password;
}
