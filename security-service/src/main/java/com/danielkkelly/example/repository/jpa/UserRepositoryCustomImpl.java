package com.danielkkelly.example.repository.jpa;

import static com.danielkkelly.example.domain.model.QSecurityPermission.securityPermission;
import static com.danielkkelly.example.domain.model.QSecurityRole.securityRole;
import static com.danielkkelly.example.domain.model.QUser.user;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.danielkkelly.example.domain.model.SecurityPermission;
import com.danielkkelly.example.domain.model.User;

public class UserRepositoryCustomImpl extends BaseRepositoryImpl<User> implements UserRepositoryCustom {

    UserRepositoryCustomImpl(EntityManager em) {
        super(User.class, em);
    }

    @Override
    public Optional<List<SecurityPermission>> findSecurityPermissions(Long id) {
        return Optional.ofNullable(
            queryFactory
                .select(securityPermission)
                .from(securityPermission)
                .join(securityPermission.securityRoles, securityRole)
                .join(securityRole.users, user)
                .where(user.id.eq(id))
                .fetch()); 
    }
}
