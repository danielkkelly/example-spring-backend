package com.danielkkelly.example.service;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

import com.danielkkelly.example.domain.model.SecurityPermission;
import com.danielkkelly.example.repository.SecurityPermissionRepository;

/**
 * Business logic around SecurityPermissions.
 */
@Service
 public class SecurityPermissionService extends BaseService<SecurityPermission> {

    /** Data Access.*/
    @Inject
    private SecurityPermissionRepository repository;

    @Override
    public SecurityPermissionRepository getRepository() {
        return repository;
    }
}
