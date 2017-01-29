package org.store.api.book;

import org.shop.model.Book;

import java.util.stream.Stream;

@FunctionalInterface
public interface BookReader {
	Stream<Book> read(String path);
}