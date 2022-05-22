package com.danielkkelly.example.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Security")
@RepositoryRestController
public class SecurityRoleController {
   
   /*
    @Inject
    private SecurityRoleService service;
   
    
    @Inject
    private SecurityRoleViewMapper securityRoleViewMapper;

    @PostMapping
    @SecurityAdminRequired
    public SecurityRole create(@RequestBody @Valid SecurityRole securityRole) {
        return service.create(securityRole);
    }

    @GetMapping
    public Page<SecurityRoleView> findAll(Pageable pageable) {
        Page<SecurityRole> page = service.findAll(pageable);
        Page<SecurityRoleView> viewPage = page.map(user -> securityRoleViewMapper.toView(user));
        return viewPage;
    }

    @GetMapping("/{id}")
    public SecurityRoleView findById(@PathVariable("id") Long id) {
        return securityRoleViewMapper.toView(service.findById(id));
    }
    */
}