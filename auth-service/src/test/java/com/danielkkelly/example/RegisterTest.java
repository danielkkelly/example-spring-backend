package com.danielkkelly.example;

import javax.inject.Inject;

import com.danielkkelly.example.controller.UserController;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RegisterTest {
	
	@Inject
	private UserController userController;


	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

}
