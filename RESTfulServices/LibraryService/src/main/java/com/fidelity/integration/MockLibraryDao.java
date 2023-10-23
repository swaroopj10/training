package com.fidelity.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fidelity.business.Book;

@Repository
public class MockLibraryDao implements LibraryDao {
	private Map<String, Book> booksMap = new HashMap<>();
	
	public MockLibraryDao() {
		booksMap.put("978-0060512804", new Book("Cryptonomicon", "Neal Stephenson", "978-0060512804"));
		booksMap.put("978-0393356687", new Book("The Overstory", "Richard Powers", "978-0393356687"));
		booksMap.put("978-0393351590", new Book("Flash Boys", "Michael Lewis", "978-0393351590"));
	}
	
	/* 
	 * Return the collection of Books.
	 * 
	 */
	@Override
	public List<Book> getBooks() {
		List<Book> books = null;
		
		// Create a list of all the Books in booksMap
		Collection<Book> allBooks = booksMap.values();
		books = new ArrayList<>(allBooks);
		
		return books;
	}
	
	/* 
	 * Return the Book specified by its isbn number.
	 * 
	 */
	@Override
	public Book getBookByIsbn (String isbn) {
		Book book = null;
		
		book = booksMap.get(isbn);
		
		return book;
	}
}
