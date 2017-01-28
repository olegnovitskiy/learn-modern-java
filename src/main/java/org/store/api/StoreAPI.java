package org.store.api;

import org.shop.model.Book;

import java.util.List;
import java.util.Optional;

public interface StoreAPI {
	List<Book> findBooks(String name, String author, int year, int pages, int publishYear);
	
	List<Book> findBooks();
	
	void takeBook(Book book);
	
	Optional<Book> findBookById(int id);

}
