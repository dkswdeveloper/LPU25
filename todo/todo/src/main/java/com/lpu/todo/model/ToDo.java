package com.lpu.todo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="todo")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int todoid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="username")
	User user;
	
	private String description;
	
	@Column(name="tododate")
	private Date todoDate;
	
	private boolean completed;

	public int getTodoid() {
		return todoid;
	}

	public void setTodoid(int todoid) {
		this.todoid = todoid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(Date todoDate) {
		this.todoDate = todoDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "ToDo [todoid=" + todoid + ", user=" + user + ", description=" + description + ", todoDate=" + todoDate
				+ ", completed=" + completed + "]";
	}
	
	
	
}
