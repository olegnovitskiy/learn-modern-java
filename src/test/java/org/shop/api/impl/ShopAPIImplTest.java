package org.shop.api.impl;

import org.junit.Before;
import org.junit.Test;
import org.shop.api.ShopAPI;
import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.api.book.BookReaderCsv;
import org.store.api.impl.StoreAPIImpl;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ShopAPIImplTest {
    private ShopAPI api;

    @Before
    public void init() {
        StoreAPI storeAPI = new StoreAPIImpl(new BookReaderCsv(), "input/books.csv");
        api = new ShopAPIImpl(storeAPI);
    }

    @Test
    public void buyOneBook() throws Exception {
        Book book = new Book(1, "Head First Java", 688, "Kathy Sierra", "N/A", "A Brain-Friendly Guide", LocalDate.parse("2016-01-01"));

        assertEquals(api.buyBook(1), book);
    }

    @Test(expected = RuntimeException.class)
    public void buyOneNotExistBook() throws Exception {
        api.buyBook(-1);
    }

}
