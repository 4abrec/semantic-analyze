package com.example.semanticanalyze.dto.auth;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
public class JWTAuthResponse {

  String accessToken;
  String tokenType = "Bearer";

  public JWTAuthResponse(String accessToken) {
    this.accessToken = accessToken;
  }
}
