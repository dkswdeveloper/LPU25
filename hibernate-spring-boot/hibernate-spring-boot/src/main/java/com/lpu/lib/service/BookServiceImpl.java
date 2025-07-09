package com.lpu.lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lpu.lib.dao.BookDao;
import com.lpu.lib.model.Book;

@Component
public class BookServiceImpl implements BookService{
	@Autowired
	BookDao bookDao;
	
	@Override
	public Book findById(int bid) {
		return bookDao.findById(bid);
	}
	@Override
	public List<Book> findByAuthor(String author) {
		return bookDao.findByAuthor(author);
	}
}



