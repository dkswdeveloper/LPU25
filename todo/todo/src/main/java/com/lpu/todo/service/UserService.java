package com.lpu.todo.service;

import java.util.List;
import java.util.Map;

import com.lpu.todo.model.User;

public interface UserService {

	User findByUsername(String username);
	User addUser(User user);
	List<User> findAll();
	boolean remove(User user);
	User updateUser(User user);
	
	boolean checkLogin(String username, String password);
	User patchUser(Map<String,String> map);
	

}