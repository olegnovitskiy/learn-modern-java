package org.store.api.impl;

import org.junit.Before;
import org.junit.Test;
import org.shop.api.ShopAPI;
import org.shop.api.impl.ShopAPIImpl;
import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.api.book.BookReaderCsv;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class StoreAPIImplTest {
    private ShopAPI api;
    private StoreAPI storeAPI;

    @Before
    public void setUp() throws Exception {
        storeAPI = new StoreAPIImpl(new BookReaderCsv(),  "input/books.csv");
        api = new ShopAPIImpl(storeAPI);
    }

    @Test
    public void findBookWithId1() throws Exception {
        Book expected = new Book(1, "Head First Java", 688, "Kathy Sierra", "N/A", "A Brain-Friendly Guide", LocalDate.parse("2016-01-01"));

        Optional<Book> actual = storeAPI.findBookById(1);

        assertEquals(Optional.ofNullable(expected), actual);
    }

    @Test
    public void findNotExistBook() throws Exception {
        Optional<Book> actual = storeAPI.findBookById(-1);

        assertEquals(Optional.empty(), actual);
    }
}
