package com.example.semanticanalyze.service.auth;

import com.example.semanticanalyze.data.entity.User;
import com.example.semanticanalyze.data.repository.RoleRepository;
import com.example.semanticanalyze.data.repository.UserRepository;
import com.example.semanticanalyze.dto.auth.JWTAuthResponse;
import com.example.semanticanalyze.dto.auth.LoginRequest;
import com.example.semanticanalyze.dto.auth.SignupRequest;
import com.example.semanticanalyze.exception.UserAlreadyExistsException;
import com.example.semanticanalyze.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record AuthService(
    AuthenticationManager authenticationManager,
    JwtTokenProvider jwtTokenProvider,
    PasswordEncoder passwordEncoder,
    UserRepository userRepository,
    RoleRepository roleRepository) {

  public JWTAuthResponse login(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword()));
    if (authentication.isAuthenticated()) {
      return new JWTAuthResponse(jwtTokenProvider.generateToken(loginRequest.getUsername()));
    } else {
      throw new UsernameNotFoundException(
          "Не найден пользователь с именем " + loginRequest.getUsername());
    }
  }

  public void signup(SignupRequest signupRequest) {
    if (!userRepository.existsByUsername(signupRequest.getUsername())) {
      var roles = roleRepository.findByNameIn(signupRequest.getRoles());
      userRepository.save(User.builder()
          .username(signupRequest.getUsername())
          .password(passwordEncoder().encode(signupRequest.getPassword()))
          .roles(roles)
          .build());
    } else {
      throw new UserAlreadyExistsException(String.format("Пользователь с логином %s уже существует", signupRequest.getUsername()));
    }
  }
}
