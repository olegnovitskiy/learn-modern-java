package org.store.api;

import java.util.List;
import org.shop.model.Book;

public interface StoreAPI {
	List<Book> findBooks(String name, String author, int year, int pages, int publishYear);
	
	List<Book> findBooks();
	
	void takeBook(Book book);
	
	Book findBookById(int id);

}
