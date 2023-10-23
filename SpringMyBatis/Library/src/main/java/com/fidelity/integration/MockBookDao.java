package com.fidelity.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fidelity.business.Book;

@Component("bookDao")
public class MockBookDao implements BookDao {
	private static List<Book> books;
	
	static {
		books = new ArrayList<>();
		books.add(new Book("Design Patterns", "Gamma, Helm, Johnson, Vlissides", "", 4));
		books.add(new Book("UML Distilled", "Martin Fowler", "", 3));
		books.add(new Book("Clean Code", "Robert Martin", "", 2));		
		books.add(new Book("Cryptonomicon", "Neal Stephenson", "", 1));		
	}

	private boolean startsWithIgnoreCase(String str, String prefix) {
		return str.regionMatches(true, 0, prefix, 0, prefix.length());
	}

	@Override
	public List<Book> queryForAllBooks() {
		return queryBooksByTitle("");
	}

	@Override
	public List<Book> queryBooksByTitle(String title) {
		return books.stream()
				.filter(book -> startsWithIgnoreCase(book.getTitle(), title))
				.collect(Collectors.toList());
	}

	public void addBook(Book book) {
		books.add(book);
	}
}
