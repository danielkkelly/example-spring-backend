package com.danielkkelly.example.apigateway.controller;

import com.danielkkelly.example.apigateway.User;
import com.danielkkelly.example.apigateway.client.UserClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

//@RestController
public class UserController {
/*
    private final UserClient userClient;

    public UserController(final UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping
    @CrossOrigin
    public Collection<User> findAllUsers() {
            return userClient.findAllUsers()
                    .getContent();
    }

 */
}
