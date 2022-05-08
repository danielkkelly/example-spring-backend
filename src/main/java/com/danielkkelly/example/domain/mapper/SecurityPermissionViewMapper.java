package com.danielkkelly.example.domain.mapper;

import java.util.List;

import com.danielkkelly.example.controller.SecurityPermissionController;
import com.danielkkelly.example.domain.dto.SecurityPermissionView;
import com.danielkkelly.example.domain.model.SecurityPermission;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public abstract class SecurityPermissionViewMapper {
    public abstract SecurityPermissionView toView(SecurityPermission securityPermission);

    public abstract List<SecurityPermissionView> toView(List<SecurityPermission> securityPermission);  

    /*
    @AfterMapping
    protected void addLinks(@MappingTarget SecurityPermissionView securityPermissionView) {
        securityPermissionView.add(linkTo(
            methodOn(SecurityPermissionController.class)
            .findById(securityPermissionView.getId()))
            .withSelfRel()); 
    }*/
}
