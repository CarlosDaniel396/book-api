package com.carlos.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.services.AuthorService;

@RestController
@RequestMapping(value = "/authors")
public class AuthorResource {

	@Autowired
	private AuthorService service;

	@GetMapping
	public ResponseEntity<Page<AuthorDTO>> findAll(Pageable pageable) {
		Page<AuthorDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
		AuthorDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

}
