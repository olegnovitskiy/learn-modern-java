package org.store.api.book;

import org.shop.model.Book;

import java.util.stream.Stream;

public class BookReaderDummy implements BookReader {
    @Override
    public Stream<Book> read(String path) {
        return Stream.empty();
    }
}
