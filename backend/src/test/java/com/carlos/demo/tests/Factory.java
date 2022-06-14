package com.carlos.demo.tests;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.entities.Author;

public class Factory {

	public static Author createAuthor() {
		Author author = new Author(1L, "Luciano Ramalho");
		return author;
	}

	public static AuthorDTO createAuthorDTO() {
		Author author = createAuthor();
		return new AuthorDTO(author);
	}
}
