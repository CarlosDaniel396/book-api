package com.carlos.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.demo.dto.AuthorDTO;
import com.carlos.demo.entities.Author;
import com.carlos.demo.repositories.AuthorRepository;
import com.carlos.demo.services.exceptions.ResourceNotFoundException;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository repository;

	@Transactional(readOnly = true)
	public Page<AuthorDTO> findAllPaged(Pageable pageable, String name) {
		Page<Author> list = repository.find(name, pageable);
		return list.map(x -> new AuthorDTO(x));
	}

	@Transactional(readOnly = true)
	public AuthorDTO findById(Long id) {
		Optional<Author> obj = repository.findById(id);
		Author entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new AuthorDTO(entity);
	}

}
