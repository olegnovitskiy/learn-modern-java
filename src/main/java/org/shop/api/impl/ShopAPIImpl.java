package org.shop.api.impl;

import org.shop.api.ShopAPI;
import org.shop.model.Book;
import org.store.api.StoreAPI;

import java.util.Objects;

public class ShopAPIImpl implements ShopAPI {
	private final StoreAPI storeAPI;
	
	public ShopAPIImpl(StoreAPI storeAPI) {
		this.storeAPI = storeAPI;
	}

	@Override
	public Book buyBook(int bookId) {
		Book book = storeAPI.findBookById(bookId);

		if (Objects.isNull(book)) {
			throw new RuntimeException("No book present");
		}

		storeAPI.takeBook(book);
		return book;
		
	}

}
