package com.lpu.todo.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lpu.todo.model.ToDo;

@SpringBootTest
class TodoServiceImplTest {

	@Autowired
	UserService userService;
	@Autowired 
	ToDoService toDoService;
	
	@Test
	void testFindAllByUsername() {
	}

	@Test
	void testAddToDo() {
		ToDo todo = new ToDo();
		todo.setCompleted(false);
		todo.setDescription("Decide project for me and model classes");
		todo.setTodoDate(Date.valueOf("2025-07-12"));
		toDoService.addToDo("yash",todo);
		
	}

	@Test
	void testUpdateToDo() {
	}

}
