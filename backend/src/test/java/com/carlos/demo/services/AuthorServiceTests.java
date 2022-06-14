package com.carlos.demo.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.entities.Author;
import com.carlos.demo.repositories.AuthorRepository;
import com.carlos.demo.tests.Factory;

@ExtendWith(SpringExtension.class)
public class AuthorServiceTests {

	@InjectMocks
	private AuthorService service;

	@Mock
	private AuthorRepository authorRepository;

	private Author author;
	private AuthorDTO authorDTO;
	private PageImpl<Author> page;

	@BeforeEach
	void setUp() throws Exception {
		author = Factory.createAuthor();
		authorDTO = Factory.createAuthorDTO();
		page = new PageImpl<>(List.of(author));

		Mockito.when(authorRepository.findAll((Pageable) any())).thenReturn(page);

	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<AuthorDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);

	}
}
