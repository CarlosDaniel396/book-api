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

import com.carlos.demo.dto.BookDTO;
import com.carlos.demo.entities.Author;
import com.carlos.demo.entities.Book;
import com.carlos.demo.repositories.AuthorRepository;
import com.carlos.demo.repositories.BookRepository;
import com.carlos.demo.services.exceptions.ResourceNotFoundException;
import com.carlos.demo.tests.Factory;

@ExtendWith(SpringExtension.class)
public class BookServiceTests {

	@InjectMocks
	private BookService service;

	@Mock
	private BookRepository repository;

	@Mock
	private AuthorRepository authorRepository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private Book book;
	private Author author;
	private BookDTO bookDTO;
	private PageImpl<Book> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		book = Factory.createBook();
		bookDTO = Factory.createBookDTO();
		author = Factory.createAuthor();
		page = new PageImpl<>(List.of(book));

		Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(book));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.when(repository.getReferenceById(existingId)).thenReturn(book);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Mockito.when(authorRepository.getReferenceById(existingId)).thenReturn(author);
		Mockito.when(authorRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<BookDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldReturnBookDTOWhenIdExists() {

		BookDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
}
