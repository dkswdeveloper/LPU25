package com.lpu.lib.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Book {
	@SequenceGenerator(name="bookidseq", initialValue = 10, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int bid;
	String title;
	String author;
	Double price;
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}
	

}
