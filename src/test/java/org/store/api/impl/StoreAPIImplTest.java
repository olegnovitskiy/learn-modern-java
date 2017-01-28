package org.store.api.impl;

import org.junit.Before;
import org.junit.Test;
import org.shop.api.ShopAPI;
import org.shop.api.impl.ShopAPIImpl;
import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.api.book.BookReaderImpl;
import org.store.util.DateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StoreAPIImplTest {
    private ShopAPI api;
    private StoreAPI storeAPI;

    @Before
    public void setUp() throws Exception {
        storeAPI = new StoreAPIImpl(new BookReaderImpl(), "csv", "input/books.csv");
        api = new ShopAPIImpl(storeAPI);
    }

    @Test
    public void findBookWithId1() throws Exception {
        Book expected = new Book(1, "Head First Java", 688, "Kathy Sierra", "N/A", "A Brain-Friendly Guide", DateUtil.strToDate("2016-01-01"));

        Book actual = storeAPI.findBookById(1);

        assertEquals(expected, actual);
    }

    @Test
    public void findNotExistBook() throws Exception {
        Book actual = storeAPI.findBookById(-1);

        assertNull(actual);
    }
}
