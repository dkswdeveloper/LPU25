package com.lpu.lib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lpu.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	@Query("select s from Student s where s.branch = :branch")
	List<Student> findBranchWiseStudents(String branch);
	
	@Query(value = "select * from student where marks >= :marks ", nativeQuery = true)
	List<Student> findGoodStudents(int marks);
	
	Student findBySname(String sname);
	
	List<Student> findByMarksGreaterThanEqual(int marks);
	
	
	

}
