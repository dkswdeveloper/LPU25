package com.lpu.lib.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.lib.dao.StudentRepository;
import com.lpu.model.Student;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository sRepo;
	
	public Student findById(int roll) {
		Optional<Student> optStudent = sRepo.findById(roll);
		return optStudent.orElse(null);
	}
	@Override
	@Transactional
	public Student add(Student student)
	{
		sRepo.save(student);
		return student;
		
	}
	

}
