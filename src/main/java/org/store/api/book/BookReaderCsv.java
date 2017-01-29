package org.store.api.book;

import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;
import org.store.util.DateUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BookReaderCsv implements BookReader {
    @Override
    public Stream<Book> read(String path) {
        List<Book> books = new ArrayList<>();

        String cvsSplitBy = ",";
        int idx = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(path).toURI()));

            for (String line: lines) {
                idx++;
                if (idx == 1) {
                    continue;
                }

                String[] tokens = line.split(cvsSplitBy);
                Book book = new Book();
                book.setId(NumberUtils.toInt(tokens[0]));
                book.setName(tokens[1]);
                book.setPages(NumberUtils.toInt(tokens[2]));
                book.setAuthor(tokens[3]);
                book.setYear(tokens[4]);
                if (book.getYear().isEmpty()) {
                    book.setYear("N/A");
                }
                book.setPreview(tokens[5]);
                book.setPublication(DateUtil.strToDate(tokens[6]));
                books.add(book);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


        return books.stream();
    }
}
