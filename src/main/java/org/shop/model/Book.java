package org.shop.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
	private int id;
	
	private String name;
	
	private int pages;
	
	private String author;
	
	private String year;
	
	private String preview;
	
	private LocalDate publication;

	public Book() {}

	public Book(int id, String name, int pages, String author, String year, String preview, LocalDate publication) {
		this.id = id;
		this.name = name;
		this.pages = pages;
		this.author = author;
		this.year = year;
		this.preview = preview;
		this.publication = publication;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public LocalDate getPublication() {
		return publication;
	}

	public void setPublication(LocalDate publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", pages=" + pages + ", author=" + author + ", year=" + year
				+ ", preview=" + preview + ", publication=" + publication + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		if (o instanceof Book) {
			Book book = (Book) o;
			return  Objects.equals(pages, book.pages)&&
					Objects.equals(name, book.name) &&
					Objects.equals(author, book.author) &&
					Objects.equals(year, book.year);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, pages, author, year);
	}
}
