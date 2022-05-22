package com.danielkkelly.example.domain.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SecurityPermissionView extends RepresentationModel<SecurityPermissionView> {
    Long id;
    String name;
    String description; 
}
