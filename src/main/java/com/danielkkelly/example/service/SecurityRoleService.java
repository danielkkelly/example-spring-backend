package com.danielkkelly.example.service;

import javax.inject.Inject;

import com.danielkkelly.example.domain.model.SecurityRole;
import com.danielkkelly.example.repository.SecurityRoleRepository;

import org.springframework.stereotype.Service;

/**
 * Business logic around SecurityRoles.
 */
@Service
public class SecurityRoleService extends BaseService<SecurityRole> {

    /** Data Access. */
    @Inject
    private SecurityRoleRepository repository;

    @Override
    public SecurityRoleRepository getRepository() {
        return repository;
    }
}