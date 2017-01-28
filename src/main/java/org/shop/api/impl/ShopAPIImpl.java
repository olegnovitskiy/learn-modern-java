package org.shop.api.impl;

import org.shop.api.ShopAPI;
import org.shop.model.Book;
import org.store.api.StoreAPI;

import java.util.Optional;

public class ShopAPIImpl implements ShopAPI {
	private final StoreAPI storeAPI;
	
	public ShopAPIImpl(StoreAPI storeAPI) {
		this.storeAPI = storeAPI;
	}

	@Override
	public Book buyBook(int bookId) {
		Optional<Book> book = storeAPI.findBookById(bookId);

		storeAPI.takeBook(book.orElseThrow(()->new RuntimeException("No book present")));
		return book.get();
		
	}

}
