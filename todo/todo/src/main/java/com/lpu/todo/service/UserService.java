package com.lpu.todo.service;

import com.lpu.todo.model.User;

public interface UserService {

	User findByUsername(String username);

	User addUser(User user);
	
	boolean checkLogin(String username, String password);

}