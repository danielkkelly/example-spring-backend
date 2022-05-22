package com.danielkkelly.example.domain.mapper;

import com.danielkkelly.example.domain.dto.UserCreateRequest;
import com.danielkkelly.example.domain.dto.UserUpdateRequest;
import com.danielkkelly.example.domain.model.User;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class UserEditMapper {
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "stringToGrantedAuthority")
    public abstract User create(UserCreateRequest request);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "stringToGrantedAuthority")
    public abstract void update(UserUpdateRequest request, @MappingTarget User user);

    @Named("stringToGrantedAuthority")
    protected Set<GrantedAuthority> stringToRole(Set<String> authorities) {
        if (authorities != null) {
            return authorities.stream().map(SimpleGrantedAuthority::new).collect(toSet());
        }
        return new HashSet<>();
    }
}