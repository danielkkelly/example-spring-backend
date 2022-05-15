package com.danielkkelly.example.repository.jpa;

import com.danielkkelly.example.domain.model.SecurityRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long> {
    
}
