package com.lpu.lib.dao;

import java.util.List;

import com.lpu.lib.model.Book;

public interface BookDao {

	// on one table CRUD OPERATIONS
	List<Book> findAll();
	Book findById(int bid);
	Book add(Book book);
	boolean remove(Book book);
	Book update(Book book);
	List<Book> findByAuthor(String author);
}
