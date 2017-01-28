package org.store.api.book;

import org.shop.model.Book;

import java.util.Collections;
import java.util.List;

/**
 * Created by olegnovitskiy on 1/28/17.
 */
public class BookReaderDummy implements BookReader {
    @Override
    public List<Book> read(String path) {
        return Collections.emptyList();
    }
}
