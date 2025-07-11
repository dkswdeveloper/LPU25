package com.lpu.todo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lpu.todo.model.User;
@SpringBootTest
class UserServiceImplTest {
	public static List<String> list = new ArrayList<>();
	
	@Autowired
	UserService userService;
	
	@Test
	void testFindByUsername() {
	User byUsername = userService.findByUsername("yash");
	assertNotNull(byUsername);
	}
	@Test
	void testFindByUsernameNotExist() {
	User byUsername = userService.findByUsername("ayush234");
	assertNull(byUsername);
	}
	@Test
	void testFindByUsernameNullValue() {
	User byUsername = userService.findByUsername(null);
	assertNull(byUsername);
	}

	@Test
	void testAddUser() {
		String username = "renu" + System.currentTimeMillis();
		User user =new User(username,  "renu", true);
		userService.addUser(user);
		list.add(username);
	}
	@AfterAll
	public static void afterAllTests()
	{
		// delete the dummy data
		
		
	}

}
