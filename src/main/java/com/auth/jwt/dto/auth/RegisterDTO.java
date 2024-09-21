package com.auth.jwt.dto.auth;

public record RegisterDTO(String name, String email, String password, String role) {
}
