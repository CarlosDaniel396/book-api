package com.carlos.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.carlos.demo.entities.Author;
import com.carlos.demo.entities.Book;

public class BookDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String edition;
	private Integer publicationYear;

	private List<AuthorDTO> authors = new ArrayList<>();

	public BookDTO() {
	}

	public BookDTO(Long id, String name,String edition, Integer publicationYear) {
		this.id = id;
		this.name = name;
		this.edition = edition;
		this.publicationYear = publicationYear;
	}

	public BookDTO(Book entity) {
		id = entity.getId();
		edition = entity.getEdition();
		publicationYear = entity.getPublicationYear();
	}

	public BookDTO(Book entity, Set<Author> authors) {
		this(entity);
		authors.forEach(author -> this.authors.add(new AuthorDTO(author)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public List<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}
}
