package com.lpu.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.lpu.todo.model.User;
import com.lpu.todo.repo.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserRepository repo;

	//server side paging
	@GetMapping("/users")
	public List<User> getUsers(@SessionAttribute(name="pagenum",required = false) Integer pagenum,
			HttpSession session)
	{
		int pagesize = 2;
		if(pagenum == null)
		{
			pagenum = 0;
			session.setAttribute("pagenum", pagenum);
		}
		else
		{
			pagenum = (Integer) session.getAttribute("pagenum");
			pagenum++;
			session.setAttribute("pagenum", pagenum);
		}
		
		PageRequest pageReq = PageRequest.of(pagenum, pagesize);
		Page<User> page = repo.findAll(pageReq);
		List<User> list = page.getContent();
		return list;
		
	}
	//client side paging
	@GetMapping("/users/{pagenum}/{pagesize}")
	public List<User> getAll(@PathVariable("pagenum") int pagenum, 
			@PathVariable ("pagesize") int pagesize)
	{
		PageRequest pageReq = PageRequest.of(pagenum, pagesize);
		Page<User> page = repo.findAll(pageReq);
		List<User> list = page.getContent();
		return list;
	}
	//client side paging using request param
	@GetMapping("/users/bypage")
	public List<User> getAllByPage(@RequestParam(name="pagenum", required = false) Integer pagenum, 
			@RequestParam (name = "pagesize", required = false) Integer pagesize)
	{
		if(pagenum == null) pagenum = 0;
		if(pagesize == null) pagesize= 2;
		PageRequest pageReq = PageRequest.of(pagenum, pagesize);
		Page<User> page = repo.findAll(pageReq);
		List<User> list = page.getContent();
		return list;
	}
}











