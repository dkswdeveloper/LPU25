package com.lpu.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.todo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	

}
