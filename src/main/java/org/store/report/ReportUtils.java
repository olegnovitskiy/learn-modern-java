package org.store.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shop.model.Book;
import org.store.api.StoreAPI;
import org.store.util.DateUtil;

public class ReportUtils {
	private final StoreAPI storeAPI;
	
	public ReportUtils(StoreAPI storeAPI) {
		this.storeAPI = storeAPI;
	}

	public Map<Integer, List<Book>> getBooksByYear() {
		List<Book> books = storeAPI.findBooks();
		Map<Integer, List<Book>> map = new HashMap<>();
		for(Book book : books) {
			Date publish = book.getPublication();
			if(publish != null) {
				int year = DateUtil.getYear(publish);
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
