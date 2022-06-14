package com.carlos.demo.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.demo.dto.BookDTO;
import com.carlos.demo.entities.Book;
import com.carlos.demo.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Transactional(readOnly = true)
	public Page<BookDTO> findAllPaged(Pageable pageRequest) {
		Page<Book> list = repository.findAll(pageRequest);
		return list.map(x -> new BookDTO(x));
	}

}
