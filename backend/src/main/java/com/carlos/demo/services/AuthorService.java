package com.carlos.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.entities.Author;
import com.carlos.demo.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository repository;

	@Transactional(readOnly = true)
	public Page<AuthorDTO> findAllPaged(Pageable pageable) {
		Page<Author> list = repository.findAll(pageable);
		return list.map(x -> new AuthorDTO(x));
	}
}
