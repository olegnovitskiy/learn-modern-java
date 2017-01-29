package org.store.api.book;

import org.junit.Before;
import org.junit.Test;
import org.shop.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BookReaderImplTest {
    private List<Book> expectedBooks;

    @Before
    public void init() {
        expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1, "Head First Java", 688, "Kathy Sierra", "N/A", "A Brain-Friendly Guide", LocalDate.parse("2016-01-01")));
        expectedBooks.add(new Book(2, "Java: A Beginner's Guide", 728, "Herbert Schildt", "2014", "", LocalDate.parse("2014-10-12")));
    }

    @Test
    public void readTwoBooksFromCsv() throws Exception {
        BookReader reader = new BookReaderCsv();
        String path = "input/books.csv";
        List<Book> actualBooks = reader.read(path).collect(Collectors.toList());

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void readTwoBooksFromJson() throws Exception {
        BookReader reader = new BookReaderJson();
        String path = "input/books.json";
        List<Book> actualBooks = reader.read(path).collect(Collectors.toList());

        assertEquals(expectedBooks, actualBooks);

    }

    @Test
    public void readAndGetEmptyList() throws Exception {
        BookReader reader = new BookReaderDummy();
        List<Book> actual = reader.read("").collect(Collectors.toList());

        assertEquals(Collections.emptyList(), actual);
    }

}
