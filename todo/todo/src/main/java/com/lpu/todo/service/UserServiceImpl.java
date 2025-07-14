package com.lpu.todo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.todo.model.User;
import com.lpu.todo.repo.UserRepository;

import jakarta.transaction.Transactional;

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

}
