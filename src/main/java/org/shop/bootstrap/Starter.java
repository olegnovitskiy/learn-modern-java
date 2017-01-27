package org.shop.bootstrap;

import org.shop.api.ShopAPI;
import org.shop.api.impl.ShopAPIImpl;
import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.api.book.BookReaderImpl;
import org.store.api.impl.StoreAPIImpl;

public class Starter {
	public static void main(String[] args) {
		StoreAPI storeAPI = new StoreAPIImpl(new BookReaderImpl(), "csv", "input/books.csv");
		ShopAPI api = new ShopAPIImpl(storeAPI);
		
		System.out.println(storeAPI.findBooks());
		System.out.println(storeAPI.findBooks("Java", "", 2005, 0, 0));
		Book book = api.buyBook(1);
		System.out.println(book);
	}

}
