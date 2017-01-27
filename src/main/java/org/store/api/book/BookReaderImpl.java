package org.store.api.book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;
import org.store.util.DateUtil;

import com.google.gson.Gson;

public class BookReaderImpl implements BookReader {
	private static final Gson GSON = new Gson();

	@Override
	public List<Book> readCsv(String path) {
		List<Book> books = new ArrayList<Book>();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int idx = 0;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);

		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				idx ++;
				if( idx == 1) {
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return books;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> readJson(String path) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		List<?> items = GSON.fromJson(reader, List.class);
		List<Book> books = new ArrayList<>();
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

		return books;
	}

	public static void main(String[] args) {
	}

}
