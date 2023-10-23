package com.fidelity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fidelity.business.Book;
import com.fidelity.integration.BookDao;

@Component("bookService")
public class BookService {
	private BookDao bookDao;
	
	public BookService() {}
	
	@Autowired
	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public List<Book> queryAllBooks() {
		List<Book> books = new ArrayList<>();

		books = bookDao.queryForAllBooks();
		
		return books;
	}
	
	public List<Book> queryBookByTitle(String title) {
		List<Book> books = new ArrayList<>();
		books = bookDao.queryBooksByTitle(title);
		return books;
	}
}
