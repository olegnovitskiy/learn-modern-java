package org.store.api.book;

import org.shop.model.Book;

import java.util.List;

@FunctionalInterface
public interface BookReader {
	List<Book> read(String path);
}
