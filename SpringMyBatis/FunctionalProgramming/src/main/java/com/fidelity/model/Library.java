package com.fidelity.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {
	private List<Book> books;
	
	public Library(List<Book> books) {
		this.books = books;
	}
	
	public void sortByPrice(){
		// TODO: replace the PriceComparator instance with a lambda expression
		// HINT: see slide 6-7
		books.sort(new PriceComparator());
	}
	
	// TODO: delete this PriceComparator class
	private static class PriceComparator implements Comparator<Book> {
		@Override
		public int compare(Book b1, Book b2) {
			return Double.compare(b1.getPrice(), b2.getPrice());
		}
	}

	public void sortByTitle() {

		// TODO: replace the anonymous inner class with a lambda expression
		books.sort(new Comparator<Book>() {
			@Override
			public int compare(Book b1, Book b2) {
				return Double.compare(b1.getPrice(), b2.getPrice());
			}
		});
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	// BONUS TODO:
	//    1. add two static methods to the Library class:
	//       compareBooksByTitle(Book b1, Book b2) 
	//       compareBooksByPrice(Book b1, Book b2) 
	//    2. In sortByTitle() and sortByPrice(), replace the lambda expressions 
	//       with method references
	// HINT: see 6-13
}
