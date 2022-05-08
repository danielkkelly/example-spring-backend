package com.danielkkelly.example.domain.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public record UserUpdateRequest (
    @NotBlank String firstName,
    @NotBlank String lastName,
    Set<String> authorities) {
  
    @Builder
    public UserUpdateRequest {
    }
}
