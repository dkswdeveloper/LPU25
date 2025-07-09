package com.lpu.lib.service;

import java.util.List;

import com.lpu.lib.model.Book;

public interface BookService {
	Book findById(int bid);
	List<Book> findByAuthor(String author);
	
	

}
