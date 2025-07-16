package com.lpu.todo.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.lpu.todo.model.User;
import com.lpu.todo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


//@Controller
// all methods of this class will return the object (JSON) ACTUAL RESPONSE AND NOT VIEW NAME

@RestController 
@RequestMapping("/api")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@GetMapping(path="/user/{username}", produces="application/json")
	public User getUserByname(@PathVariable ("username") String username)
	{
		log.debug("request for user {}", username);
		return userService.findByUsername(username); //returns actual data
	}
	
	@GetMapping(path="/user", produces="application/json")
	public User getUser(@RequestParam("username") String username, HttpServletRequest request)
	{
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements())
		{
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + ": " + headerValue);
		}
		return userService.findByUsername(username); //returns name of view 
	}
	@GetMapping("/qualification" )
	
	public String saveQualification(@RequestParam Map<String, String> map,
			HttpServletRequest request, HttpSession session, 
			@SessionAttribute String username)
	{
		request.getSession().getAttribute("username");
		
		log.debug("{}", map);
		return "qualification saved";
	}
	
	
	
	
	@GetMapping(path="/users", produces="application/json")
	public List<User> findAll()
	{
		return userService.findAll();
	}
	@PostMapping(path="/user", consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User user) {
		User added = userService.addUser(user);
		return added;
	}
	@DeleteMapping(path="/user", consumes = "application/json", produces = "application/json")
	public String deleteUser(@RequestBody User user) {
		boolean removed = userService.remove(user);
		if(removed) return "User " + user.getUsername() + " deleted ";
		else return "FAILED Deleting " + user.getUsername();
	}
	@PutMapping(path = "/user", consumes = "application/json", produces = "application/json")
	public User putMethodName(@RequestBody User user) {
		return userService.updateUser(user);
	}
	@PatchMapping(path="/user", consumes = "application/json", produces = "application/json")
	public User patchUser(@RequestBody Map<String, String> map)
	{
		User patched = userService.patchUser(map);
		return patched;
	}
	
	

}

















