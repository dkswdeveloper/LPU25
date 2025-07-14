package com.lpu.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.todo.model.ToDo;
import com.lpu.todo.model.User;
import com.lpu.todo.repo.ToDoRepository;
import com.lpu.todo.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements ToDoService {
	@Autowired
	ToDoRepository todoRepo;
	
	@Autowired
	UserRepository userRepo;

	@Transactional
	public List<ToDo> findAllByUsername(String usernmae)
	{
		return todoRepo.findByUsername(usernmae);
	}
	@Override
	@Transactional
	public ToDo addToDo(String username, ToDo toDo)
	{
		Optional<User> opt = userRepo.findById(username);
		if(opt.isEmpty()) { // no user with this name exists
			return null;
		}
		User user =opt.get();
		toDo.setUser(user);
		ToDo saved = todoRepo.save(toDo);
		return saved;
	}
	@Override
	@Transactional
	public ToDo UpdateToDo(ToDo toDo)
	{
		ToDo updated = todoRepo.save(toDo);
		return updated;
	}
}
