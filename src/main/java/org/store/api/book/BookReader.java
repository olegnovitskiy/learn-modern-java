package org.store.api.book;

import java.util.List;

import org.shop.model.Book;

public interface BookReader {
	List<Book> readCsv(String path);
	
	List<Book> readJson(String path);
}
