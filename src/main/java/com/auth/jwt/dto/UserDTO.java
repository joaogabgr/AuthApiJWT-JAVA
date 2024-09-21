package com.auth.jwt.dto;

import com.auth.jwt.model.User.UserRole;

public record UserDTO(String name, String email, UserRole role) {
}
