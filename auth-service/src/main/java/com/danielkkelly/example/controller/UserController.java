package com.danielkkelly.example.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.danielkkelly.example.domain.assembler.UserRepresentationModelAssembler;
import com.danielkkelly.example.domain.dto.SecurityPermissionView;
import com.danielkkelly.example.domain.dto.UserCreateRequest;
import com.danielkkelly.example.domain.dto.UserModel;
import com.danielkkelly.example.domain.dto.UserView;
import com.danielkkelly.example.domain.mapper.SecurityPermissionViewMapper;
import com.danielkkelly.example.domain.mapper.UserViewMapper;
import com.danielkkelly.example.domain.model.User;
import com.danielkkelly.example.security.UserAdminRequired;
import com.danielkkelly.example.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users")
@RepositoryRestController
@UserAdminRequired
public class UserController {

    //private RepositoryEntityLinks entityLinks;

    @Inject
    private UserService service; 

    @Inject
    private UserViewMapper userViewMapper;

    @Inject
    private SecurityPermissionViewMapper securityPermissionViewMapper;

    @PostMapping("/users")
    public @ResponseBody PersistentEntityResource create(@RequestBody @Valid UserCreateRequest request, 
                            PersistentEntityResourceAssembler pera) {
        return pera.toModel(service.create(request));
    }

    @GetMapping
    public Page<UserView> findAll(Pageable pageable) {
        Page<User> page = service.findAll(pageable);
        Page<UserView> viewPage = page.map(user -> userViewMapper.toView(user));
        return viewPage;
    }
   
    /*
    @GetMapping("/{id}")
    public UserView findById(@PathVariable("id") Long id) {
        return userViewMapper.toView(service.findById(id));
    }*/
    
    /*

    @PostMapping("/{id}/roles")
    public UserSecurityRole addRole(@PathVariable("id") Long id,
                                    @RequestBody @Valid UserAddRoleRequest request) {
        return service.addSecurityRole(id, request.getSecurityRoleId());
    }

    @GetMapping("/{id}/roles")
    public List<SecurityRoleView> findRoles(@PathVariable("id") Long id) {
        return securityRoleViewMapper.toView(
                service.findSecurityRoles(id));
    }

    @DeleteMapping("/{id}/roles/{roleId}") 
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id, 
                                           @PathVariable("roleId") Long securityRoleId) {
        userSecurityRoleService.delete(id, securityRoleId);

        return ResponseEntity.noContent().build();
    }
    */

    @GetMapping("/users/{id}/permissions")
    public List<SecurityPermissionView> findPermissions(@PathVariable("id") Long id) {
        return securityPermissionViewMapper.toView(
            service.findSecurityPermissions(id));
    }
}