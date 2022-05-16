package com.danielkkelly.example.domain.assembler;

import com.danielkkelly.example.controller.UserController;
import com.danielkkelly.example.domain.dto.UserModel;
import com.danielkkelly.example.domain.model.User;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.inject.Inject;

@Component
public class UserRepresentationModelAssembler 
    implements RepresentationModelAssembler<User, UserModel> {

    private RepositoryEntityLinks entityLinks;

    @Inject
	public UserRepresentationModelAssembler(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

    @Override
    public UserModel toModel(User entity) {
        UserModel userModel = UserModel.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .build();

        Link userLink = entityLinks.linkToItemResource(User.class, entity.getId())
                                   .withSelfRel();
        
        //Link rolesLink = linkTo(User.class.getSecurityRoles()).withRel("roles");

        Link permsLink = linkTo(methodOn(UserController.class)
                .findPermissions(entity.getId()))
                .withRel("permissions");    

        userModel.add(userLink);
        //userModel.add(rolesLink);
        userModel.add(permsLink);

        return userModel;
    }
    
    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserModel> userModels = 
            RepresentationModelAssembler.super.toCollectionModel(entities);
        return userModels;
    }
}
