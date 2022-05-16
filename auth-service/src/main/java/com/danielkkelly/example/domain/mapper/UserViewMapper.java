package com.danielkkelly.example.domain.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import com.danielkkelly.example.domain.dto.UserView;
import com.danielkkelly.example.domain.model.User;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

  public abstract UserView toView(User user);

  public abstract List<UserView> toView(List<User> users);

    /*
    @AfterMapping
    protected void addLinks(@MappingTarget UserView userView) {
        userView.add(linkTo(
            methodOn(SecurityPermissionController.class)
            .findById(securityPermissionView.getId()))
            .withSelfRel()); 
    }*/
}