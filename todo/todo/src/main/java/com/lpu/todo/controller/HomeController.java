package com.lpu.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lpu.todo.model.ToDo;
import com.lpu.todo.service.ToDoService;
import com.lpu.todo.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired 
	ToDoService todoService;
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String requestMethodName() {
		return "todohome"; // go to /WEB-INF/views/todohome.jsp
	}
	@PostMapping("/login")
	public String loginPage(@RequestParam("username") String username, 
			@RequestParam("password") String password, ModelMap map)
	{	if(userService.checkLogin(username, password))
		{
			List<ToDo> list = todoService.findAllByUsername(username);
			map.put("list",  list);
			map.put("username",  username);
			return "todos-view"; // go TO /WEB-INF/views/todos-view.jsp 
		}
		else
		{
			map.put("msg",  "Invalide username or password");
			return "todohome";
		}
	}
	
	

}
