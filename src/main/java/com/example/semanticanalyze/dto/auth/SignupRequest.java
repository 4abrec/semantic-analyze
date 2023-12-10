package com.example.semanticanalyze.dto.auth;

import static lombok.AccessLevel.PRIVATE;

import com.example.semanticanalyze.data.entity.RoleEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
public class SignupRequest {

  String username;
  String password;
  List<RoleEnum> roles;
}
