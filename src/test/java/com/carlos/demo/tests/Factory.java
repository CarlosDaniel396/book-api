package com.carlos.demo.tests;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.dto.BookDTO;
import com.carlos.demo.entities.Author;
import com.carlos.demo.entities.Book;

public class Factory {
	
	public static Book createBook() {
		Book book = new Book(1L, "Crime and Punishment", 1, 1866);
		book.getAuthors().add(createAuthor());
		return book;
	}

	public static BookDTO createBookDTO() {
		Book book = createBook();
		return new BookDTO(book);
	}

	public static Author createAuthor() {
		Author author = new Author(1L, "Fyodor Dostoyevsky");
		return author;
	}

	public static AuthorDTO createAuthorDTO() {
		Author author = createAuthor();
		return new AuthorDTO(author);
	}	
}
