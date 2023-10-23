package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Book;

public interface BookDao {

	List<Book> queryForAllBooks();

	List<Book> queryBooksByTitle(String title);

}