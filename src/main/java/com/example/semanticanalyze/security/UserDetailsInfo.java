package com.example.semanticanalyze.security;

import static lombok.AccessLevel.PRIVATE;

import com.example.semanticanalyze.data.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserDetailsInfo implements UserDetails {

  String username;
  String password;
  List<GrantedAuthority> authorities;

  public UserDetailsInfo(User user) {
    username = user.getUsername();
    password = user.getPassword();
    authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
