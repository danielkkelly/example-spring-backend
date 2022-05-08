package com.danielkkelly.example.repository;

import java.util.Optional;

import com.danielkkelly.example.domain.model.User;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends BaseRepository<User, Long>, UserRepositoryCustom {
   
    @RestResource(path = "byUsername")
    Optional<User> findByUsername(String username);

    @Override
    <S extends User> S save(S entity);
} 
