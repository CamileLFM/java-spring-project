
package com.ifgram.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDtos {
    public static class SignupRequest {
        @Email @NotBlank public String email;
        @NotBlank public String password;
        @NotBlank public String telefone;
    }
    public static class LoginRequest {
        @Email @NotBlank public String email;
        @NotBlank public String password;
    }
    public static class TokenResponse {
        public String token;
        public long expiresIn;
        public TokenResponse(String token, long expiresIn){ this.token = token; this.expiresIn = expiresIn; }
    }
}
