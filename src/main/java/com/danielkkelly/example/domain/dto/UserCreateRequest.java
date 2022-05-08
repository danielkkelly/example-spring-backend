package com.danielkkelly.example.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public record UserCreateRequest(
  @NotBlank @Email String username,
  @NotBlank String password,
  @NotBlank String rePassword,
  @NotBlank String firstName,
  @NotBlank String lastName,
  Set<String> authorities) {

  public UserCreateRequest (
    String username,
    String password,
    String rePassword,
    String firstName,
    String lastName) {
    this(username, password, rePassword, firstName, lastName, new HashSet<>());
  }
}