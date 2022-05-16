package com.danielkkelly.example.domain.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SecurityRoleView extends RepresentationModel<SecurityRoleView> {
        Long id;
        String name;
        String description;
}