package com.lpu.lib.service;

import com.lpu.model.Student;

import jakarta.transaction.Transactional;

public interface StudentService {

	Student add(Student student);

}