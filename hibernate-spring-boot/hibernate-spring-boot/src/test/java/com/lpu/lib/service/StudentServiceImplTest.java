package com.lpu.lib.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import com.lpu.model.Student;

@SpringBootTest
class StudentServiceImplTest {

	@Autowired
	StudentService studentService;
	
	@Autowired
	ConfigurableApplicationContext context;
	
	@Test
	void testFindById() {
//		StudentService studentService = new StudentServiceImpl(); // will it work ? 
		Student student = studentService.findById(101);
		assertNotNull(student);
		assertEquals(101,student.getRoll());
		assertEquals("Mohit", student.getSname());
	}

	@Test
	void testAdd() {
		
	}

}
