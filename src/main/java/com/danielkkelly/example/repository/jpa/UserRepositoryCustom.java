package com.danielkkelly.example.repository.jpa;

import java.util.List;
import java.util.Optional;

import com.danielkkelly.example.domain.model.SecurityPermission;

import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepositoryCustom {
    @RestResource(path = "byPermissions")
    Optional<List<SecurityPermission>> findSecurityPermissions(Long id);
}
