package org.store.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.api.book.BookReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreAPIImpl implements StoreAPI {
	private List<Book> books;

	public StoreAPIImpl(BookReader reader, String path) {
        books = reader.read(path).collect(Collectors.toList());
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
			if (year != 0 && (Objects.isNull(book.getYear()) || year != NumberUtils.toInt(book.getYear()))) {
				continue;
			}
			if (pages != 0 && pages != book.getPages()) {
				continue;
			}
			if (publishYear != 0 && publishYear != book.getPublication().getYear()) {
				continue;
			}

			target.add(book);
		}

		return target;
	}

	@Override
	public Optional<Book> findBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst();
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
