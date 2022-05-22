package com.danielkkelly.example.controller;

import java.util.List;

import javax.inject.Inject;

import com.danielkkelly.example.domain.dto.SecurityPermissionView;
import com.danielkkelly.example.domain.dto.SecurityRoleView;
import com.danielkkelly.example.domain.dto.UserView;
import com.danielkkelly.example.domain.mapper.SecurityPermissionViewMapper;
import com.danielkkelly.example.domain.mapper.SecurityRoleViewMapper;
import com.danielkkelly.example.domain.mapper.UserViewMapper;
import com.danielkkelly.example.domain.model.User;
import com.danielkkelly.example.service.UserService;

import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Profile")
@RestController
@RequestMapping(path = "/me")
public class MeController {
    @Inject
    private UserService service; 

    @Inject
    private UserViewMapper userViewMapper;

    @Inject
    private SecurityRoleViewMapper securityRoleViewMapper;
    
    @Inject
    private SecurityPermissionViewMapper securityPermissionViewMapper;

    @GetMapping
    public UserView findMe(Authentication auth) {
        UserView userView = userViewMapper.toView(
            service.findByUsername(auth.getName()));

        Link rolesLink = linkTo(methodOn(MeController.class)
                .findMySecurityRoles(auth))
                .withRel("roles");

        Link permsLink = linkTo(methodOn(MeController.class)
                .findMySecurityPermissions(auth))
                .withRel("permissions");

        userView.add( linkTo(MeController.class).withSelfRel() );
        userView.add(rolesLink);
        userView.add(permsLink);

        return userView;
    }

    @GetMapping("roles")
    public List<SecurityRoleView> findMySecurityRoles(Authentication auth) {
        User user = service.findByUsername(auth.getName());

        return securityRoleViewMapper.toView(
            service.findSecurityRoles(user.getId()));
    }

    @GetMapping("permissions")
    public List<SecurityPermissionView> findMySecurityPermissions(Authentication auth) {
        User user = service.findByUsername(auth.getName());

        return securityPermissionViewMapper.toView(
            service.findSecurityPermissions(user.getId()));
    }
}