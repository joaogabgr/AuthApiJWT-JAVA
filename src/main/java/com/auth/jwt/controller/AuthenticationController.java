package com.auth.jwt.controller;

import com.auth.jwt.dto.UserDTO;
import com.auth.jwt.dto.web.ResponseModelDTO;
import com.auth.jwt.excepition.SystemContextException;
import com.auth.jwt.infra.security.TokenService;
import com.auth.jwt.dto.auth.AuthenticationDTO;
import com.auth.jwt.dto.auth.LoginResponseDTO;
import com.auth.jwt.dto.auth.RegisterDTO;
import com.auth.jwt.model.User.User;
import com.auth.jwt.repositories.UserRepository;
import com.auth.jwt.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) throws SystemContextException {
        try {
            var token = this.authenticationService.login(data);
            var response = new ResponseModelDTO(new LoginResponseDTO(token));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new SystemContextException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) throws SystemContextException {
        try {
            var user = this.authenticationService.register(data);
            var response = new ResponseModelDTO(user);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new SystemContextException(e.getMessage());
        }
    }
}
