package com.lpu.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.todo.model.User;
import com.lpu.todo.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService  {
	@Autowired
	UserRepository userRepo;
	
	@Override
	public User findByUsername(String username)
	{
		return userRepo.findById(username).get();
	}
	@Override
	public User addUser(User user)
	{
		userRepo.save(user);
		return user;
	}

}
