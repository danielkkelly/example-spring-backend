package com.danielkkelly.example.domain.mapper;

import java.util.List;

import com.danielkkelly.example.controller.SecurityRoleController;
import com.danielkkelly.example.domain.dto.SecurityRoleView;
import com.danielkkelly.example.domain.model.SecurityRole;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public abstract class SecurityRoleViewMapper {
    public abstract SecurityRoleView toView(SecurityRole securityRole);

    public abstract List<SecurityRoleView> toView(List<SecurityRole> securityRole); 

    @AfterMapping
    protected void addLinks(@MappingTarget SecurityRoleView securityRoleView) {
        /*
        securityRoleView.add(linkTo(
            methodOn(SecurityRoleController.class).
            .findById(securityRoleView.getId()))
            .withSelfRel()); */
    }
}