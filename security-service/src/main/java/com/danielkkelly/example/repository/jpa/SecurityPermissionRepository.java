package com.danielkkelly.example.repository.jpa;

import com.danielkkelly.example.domain.model.SecurityPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Permissions are created by developers as part of application development's 
 * authorization model.  We will not support create and delete from resources.
 */
@RepositoryRestResource(collectionResourceRel = "permissions", path = "permissions")
public interface SecurityPermissionRepository extends JpaRepository<SecurityPermission, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(final Long id);

    @Override
    @RestResource(exported = false)
    <S extends SecurityPermission> S save(S entity);
}
