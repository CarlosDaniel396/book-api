package com.carlos.demo.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.dto.BookDTO;
import com.carlos.demo.entities.Author;
import com.carlos.demo.entities.Book;
import com.carlos.demo.repositories.AuthorRepository;
import com.carlos.demo.repositories.BookRepository;
import com.carlos.demo.services.exceptions.DatabaseException;
import com.carlos.demo.services.exceptions.ResourceNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	public Page<BookDTO> findAllPaged(Pageable pageRequest) {
		Page<Book> list = repository.findAll(pageRequest);
		return list.map(x -> new BookDTO(x));
	}

	@Transactional(readOnly = true)
	public BookDTO findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		Book entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new BookDTO(entity, entity.getAuthors());
	}

	@Transactional
	public BookDTO insert(BookDTO dto) {
		Book entity = new Book();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BookDTO(entity);
	}

	@Transactional
	public BookDTO update(Long id, BookDTO dto) {
		try {
			Book entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new BookDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(BookDTO dto, Book entity) {

		entity.setName(dto.getName());
		entity.setEdition(dto.getEdition());
		entity.setPublicationYear(dto.getPublicationYear());

		entity.getAuthors().clear();
		for (AuthorDTO authorDTO : dto.getAuthors()) {
			Author author = authorRepository.getReferenceById(authorDTO.getId());
			entity.getAuthors().add(author);
		}
	}
}
