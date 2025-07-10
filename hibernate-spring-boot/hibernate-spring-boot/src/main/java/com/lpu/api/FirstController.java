package com.lpu.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lpu.lib.service.StudentService;
import com.lpu.model.Student;

@Controller
public class FirstController {
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value = "/home", method=RequestMethod.GET) // control shift O
	public String requestMethodName(ModelMap modelMap) {
		// validate the request, transform the request
		// go to service : fetch the data (MODEL) 
		Student student = studentService.findById(101);
		// To PUT THE DATA IN A VIEW 
		modelMap.put("student", student.toString());
		return "first"; // View Name  /WEB-INF/views/first.jsp
	}
	

}
