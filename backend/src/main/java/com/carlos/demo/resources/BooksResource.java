package com.carlos.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.demo.dto.BookDTO;
import com.carlos.demo.services.BookService;

@RestController
@RequestMapping(value = "/books")
public class BooksResource {

	@Autowired
	private BookService service;

	@GetMapping
	public ResponseEntity<Page<BookDTO>> findAll(Pageable pageable) {
		Page<BookDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
}
