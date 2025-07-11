package com.lpu.todo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lpu.todo.model.User;
@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	UserService userService;
	
	@Test
	void testFindByUsername() {
	User byUsername = userService.findByUsername("yash");
	assertNotNull(byUsername);
	}

	@Test
	void testAddUser() {
		
	}

}
