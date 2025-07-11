package com.lpu.todo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lpu.todo.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer>{
	@Query("select t from ToDo t where t.user.username = :username")
	List<ToDo> findByUsername(String username);

}
