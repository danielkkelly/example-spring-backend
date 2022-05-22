package com.danielkkelly.example.domain.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserView extends RepresentationModel<UserView> {
  String id;
  String username;
  String firstName;
  String lastName;
}