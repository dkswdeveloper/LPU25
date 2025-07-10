package com.lpu.lib.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lpu.lib.dao.StudentRepository;
import com.lpu.model.Student;

import jakarta.transaction.Transactional;

@Component
public class LibraryServiceImpl {
	
	@Autowired
	StudentRepository studentRepo;
	
//	@Autowired
//	BookRepository bookRepository;
	
//	@Autowired
//	IssueRepository issueRepository;
	
	@Transactional
	public void issueBook(int bid, int roll)
	{
		Student student = studentRepo.findById(roll).get();
		
		
	}

}
