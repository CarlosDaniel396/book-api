package com.carlos.demo.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
import com.carlos.demo.services.exceptions.ResourceNotFoundException;
import com.carlos.demo.tests.Factory;

@ExtendWith(SpringExtension.class)
public class AuthorServiceTests {

	@InjectMocks
	private AuthorService service;

	@Mock
	private AuthorRepository repository;

	private long existingId;
	private long nonExistingId;
	private Author author;
	private AuthorDTO authorDTO;
	private PageImpl<Author> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;

		author = Factory.createAuthor();
		authorDTO = Factory.createAuthorDTO();
		page = new PageImpl<>(List.of(author));

		Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(author));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.find(any(), any())).thenReturn(page);

		Mockito.when(repository.getReferenceById(existingId)).thenReturn(author);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<AuthorDTO> result = service.findAllPaged(pageable, "");

		Assertions.assertNotNull(result);

	}

	@Test
	public void findByIdShouldReturnAuthorDTOWhenIdExists() {

		AuthorDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}

}
