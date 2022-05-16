package com.danielkkelly.example.controller;

import com.danielkkelly.example.security.SecurityAdminRequired;

import org.springframework.data.rest.webmvc.RepositoryRestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Security")
@RepositoryRestController
@SecurityAdminRequired
public class SecurityPermissionController {
}
