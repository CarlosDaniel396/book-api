package com.carlos.demo.dto;

import java.io.Serializable;

import com.carlos.demo.entities.Author;

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public AuthorDTO() {
	}

	public AuthorDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public AuthorDTO(Author entity) {
		id = entity.getId();
		name = entity.getName();
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

}
