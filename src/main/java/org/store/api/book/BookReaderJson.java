package org.store.api.book;

import com.google.gson.Gson;
import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class BookReaderJson implements BookReader {
    private static final Gson GSON = new Gson();

    @Override
    public Stream<Book> read(String path) {
        List<Book> books = new ArrayList<>();

        // TODO Replace is&reader by NIO.2
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            List<?> items = GSON.fromJson(reader, List.class);
            for (int i = 0; i < items.size(); i++) {
                Object item = items.get(i);
                Map<String, String> data = (Map<String, String>) item;
                Book book = new Book();
                book.setId(NumberUtils.toInt(data.get("id")));
                book.setName(data.get("name"));
                book.setPages(NumberUtils.toInt(data.get("pages")));
                book.setAuthor(data.get("author"));
                book.setYear(data.get("year"));
                if (book.getYear().isEmpty()) {
                    book.setYear("N/A");
                }
                if (!Objects.isNull(data.get("preview"))) {
                    book.setPreview("");
                }
                else {
                    book.setPreview(data.get("preview"));
                }
                book.setPublication(LocalDate.parse(data.get("publication")));

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books.stream();
    }
}
