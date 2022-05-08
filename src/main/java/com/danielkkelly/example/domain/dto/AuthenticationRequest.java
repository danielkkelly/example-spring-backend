package com.danielkkelly.example.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record AuthenticationRequest (
    @NotNull @Email String username,
    @NotNull String password) {
}
