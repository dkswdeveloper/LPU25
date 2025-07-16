package com.lpu.todo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.todo.model.User;
import com.lpu.todo.repo.UserRepository;

import jakarta.transaction.Transactional;
/*
 * GET : CLICK ON URL, default form submission , browser type url enter 
 * request param : visible url NOT FOR USERID AND PASSWORD
 * POST : NOT LIMIT ON DATA, ALL REQUEST PARAM not in URL, SEND THE BODY 
 * DATA : FORM-DATA, ENDCODE, MULTIPART FORM DATA, JSON, XML TEXT , RequestBody 
 * 
 * URL ? 
 * METHOD
 * HEADER
 * AUTHORIZATION
 * COOKIES
 * BODY (not in get)
 * 
 * 
 */



@Service
public class UserServiceImpl implements UserService  {
	private static final Logger log =LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserRepository userRepo;
	
	@Override
	@Transactional
	public User findByUsername(String username)
	{
		log.debug("findByUsername called searching for "+ username);
		try
		{
		Optional<User> optional = userRepo.findById(username);
		//if there is no element, throw NoSuchEleemntException 
		return optional.orElse(null); // if found reutrn user, else null
		}
		catch(Exception e)
		{
			log.error(username + " was not found");
			return null;
		}
	}
	@Override
	@Transactional
	public User addUser(User user)
	{
		log.debug("trying to add user " + user.getUsername());
		userRepo.save(user);
		return user;
	}
	@Override
	@Transactional
	public boolean checkLogin(String username, String password) {
		Optional<User> opt = userRepo.findById(username);
		if(opt.isPresent())
		{
			String passInDb = opt.get().getPassword();
			if(passInDb.equals(password))
			{
				return true;
			}
		}
		return false;
	}
	@Override
	@Transactional
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	@Override
	@Transactional
	public boolean remove(User user) {
		if(userRepo.findById(user.getUsername()).isPresent())
		{
			userRepo.deleteById(user.getUsername());
			return true;
		}
		return false;
	}
	@Override
	@Transactional
	public User updateUser(User user) {
		if(userRepo.findById(user.getUsername()).isPresent())
		{
			// if user is transient 
			userRepo.save(user);
			return user;
		}
		return null;
	}
	@Override
	public User patchUser(Map<String, String> map) {
		User user= userRepo.findById(map.get("username")).get();
		
		if(map.containsKey("enabled")) user.setEnabled(Boolean.valueOf(map.get("enabled")));
		if(map.containsKey("password")) user.setPassword(map.get("password")) ;
		return user;
	}

}
