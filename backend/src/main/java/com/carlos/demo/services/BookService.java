package com.carlos.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.demo.dto.BookDTO;
import com.carlos.demo.entities.Book;
import com.carlos.demo.repositories.BookRepository;
import com.carlos.demo.services.exceptions.ResourceNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Transactional(readOnly = true)
	public Page<BookDTO> findAllPaged(Pageable pageRequest) {
		Page<Book> list = repository.findAll(pageRequest);
		return list.map(x -> new BookDTO(x));
	}

	@Transactional(readOnly = true)
	public BookDTO findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		Book entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new BookDTO(entity);
	}

}
