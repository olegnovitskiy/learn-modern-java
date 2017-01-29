package org.store.report;

import org.shop.model.Book;
import org.store.api.StoreAPI;

import java.time.LocalDate;
import java.util.*;

public class ReportUtils {
	private final StoreAPI storeAPI;
	
	public ReportUtils(StoreAPI storeAPI) {
		this.storeAPI = storeAPI;
	}

	public Map<Integer, List<Book>> getBooksByYear() {
		List<Book> books = storeAPI.findBooks();
		Map<Integer, List<Book>> map = new HashMap<>();

		for(Book book : books) {
			LocalDate publish = book.getPublication();

			if(!Objects.isNull(publish)) {
				int year = publish.getYear();
				if(map.containsKey(year)) {
					List<Book> items = map.get(year);
					items.add(book);
				} else {
					List<Book> items = new ArrayList<>();
					items.add(book);
					map.put(year, items);
				}
			}
		}
		
		return map;
	}

}
