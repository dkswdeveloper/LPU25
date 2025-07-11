package com.lpu.todo.service;

import java.util.List;

import com.lpu.todo.model.ToDo;

import jakarta.transaction.Transactional;

public interface ToDoService {

	ToDo addToDo(ToDo toDo);

	ToDo UpdateToDo(ToDo toDo);
	public List<ToDo> findAllByUsername(String usernmae);

}