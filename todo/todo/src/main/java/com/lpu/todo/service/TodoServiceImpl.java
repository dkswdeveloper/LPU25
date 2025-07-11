package com.lpu.todo.service;

import java.beans.Transient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.todo.model.ToDo;
import com.lpu.todo.repo.ToDoRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements ToDoService {
	@Autowired
	ToDoRepository todoRepo;

	@Transactional
	public List<ToDo> findAllByUsername(String usernmae)
	{
		return todoRepo.findByUsername(usernmae);
	}
	@Override
	@Transactional
	public ToDo addToDo(ToDo toDo)
	{
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
