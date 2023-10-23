package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Book;

public interface LibraryDao {

	List<Book> getBooks();

	Book getBookByIsbn(String isbn);

}