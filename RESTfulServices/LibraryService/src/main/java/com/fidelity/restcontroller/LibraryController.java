package com.fidelity.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.Book;
import com.fidelity.business.BookList;
import com.fidelity.integration.LibraryDao;

@RestController
@RequestMapping("/library")
public class LibraryController {
	@Autowired
	private LibraryDao dao;
	
	public LibraryController() {
	}

	// GET http://localhost:8080/library/books
	@GetMapping("/books")
	public ResponseEntity<BookList> getBooks() {
		
		ResponseEntity<BookList> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		BookList books = new BookList (dao.getBooks());
		if(books != null) {
			response = ResponseEntity.status(HttpStatus.OK).body(books);
		}
		return response;
	}

	// GET http://localhost:8080/library/978-0060512804
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String id) {

		Book book = dao.getBookByIsbn(id);
		ResponseEntity<Book> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		if(book != null) {
			response =  ResponseEntity.status(HttpStatus.OK).body(book);
		}
		return response;
	}
}
