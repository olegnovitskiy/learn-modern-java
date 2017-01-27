package org.store.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.api.book.BookReader;
import org.store.util.DateUtil;

public class StoreAPIImpl implements StoreAPI {
	private List<Book> books;

	public StoreAPIImpl(BookReader reader, String source, String path) {
		if ("csv".equals(source)) {
			books = reader.readCsv(path);
		} else {
			books = reader.readJson(path);
		}
	}

	@Override
	public List<Book> findBooks(String name, String author, int year, int pages, int publishYear) {
		List<Book> target = new ArrayList<>();
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if (!StringUtils.isEmpty(name) && !book.getName().contains(name)) {
				continue;
			}
			if (!StringUtils.isEmpty(author) && !book.getAuthor().contains(author)) {
				continue;
			}
			if (year != 0 && (book.getYear() == null || year != NumberUtils.toInt(book.getYear()))) {
				continue;
			}
			if (pages != 0 && pages != book.getPages()) {
				continue;
			}
			if (publishYear != 0 && publishYear != DateUtil.getYear(book.getPublication())) {
				continue;
			}
			target.add(book);
		}

		return target;
	}

	@Override
	public Book findBookById(int id) {
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

	@Override
	public List<Book> findBooks() {
		return books;
	}

	@Override
	public void takeBook(Book book) {
		books.remove(book);
	}

}
