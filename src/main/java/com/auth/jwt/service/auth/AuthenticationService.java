package com.auth.jwt.service.auth;

import com.auth.jwt.dto.UserDTO;
import com.auth.jwt.dto.auth.AuthenticationDTO;
import com.auth.jwt.dto.auth.RegisterDTO;
import com.auth.jwt.excepition.SystemContextException;
import com.auth.jwt.infra.security.TokenService;
import com.auth.jwt.model.User.User;
import com.auth.jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(AuthenticationDTO data) throws SystemContextException {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            return tokenService.generateToken((User) auth.getPrincipal());
        } catch (Exception e) {
            throw new SystemContextException(e.getMessage());
        }
    }

    public UserDTO register(RegisterDTO data) throws SystemContextException {
        try {
            if (this.userRepository.findByEmail(data.email()) != null) {
                throw new SystemContextException("Email already registered");
            }

            String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
            User user = new User(data.name(), data.email(), encodedPassword, data.role());
            this.userRepository.save(user);
            return new UserDTO(user.getName(), user.getEmail(), user.getRole());
        } catch (Exception e) {
            throw new SystemContextException(e.getMessage());
        }
    }
}
