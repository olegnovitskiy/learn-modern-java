package org.store.api.book;

import com.google.gson.Gson;
import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;
import org.store.util.DateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookReaderImpl implements BookReader {
	private static final Gson GSON = new Gson();

	@Override
	public List<Book> readCsv(String path) {
		List<Book> books = new ArrayList<>();

		String cvsSplitBy = ",";
		int idx = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(path).toURI()));

            for (String line: lines
                 ) {
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


		return books;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> readJson(String path) {
        List<Book> books = new ArrayList<>();

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
                if (data.containsKey("year")) {
                    book.setYear(data.get("year"));
                } else {
                    book.setYear("N/A");
                }

                book.setPreview(data.get("preview"));
                book.setPublication(DateUtil.strToDate(data.get("publication")));

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
	}

	public static void main(String[] args) {
	}

}
