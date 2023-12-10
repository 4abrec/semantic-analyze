package com.example.semanticanalyze.controller;


import static lombok.AccessLevel.PRIVATE;

import com.example.semanticanalyze.dto.auth.JWTAuthResponse;
import com.example.semanticanalyze.dto.auth.LoginRequest;
import com.example.semanticanalyze.dto.auth.SignupRequest;
import com.example.semanticanalyze.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthController {

  AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/signup")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Void> signup(@RequestBody SignupRequest signupRequest) {
    authService.signup(signupRequest);
    return ResponseEntity.ok().build();
  }
}