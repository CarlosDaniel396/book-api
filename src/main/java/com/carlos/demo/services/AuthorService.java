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
import com.carlos.demo.entities.Author;
import com.carlos.demo.repositories.AuthorRepository;
import com.carlos.demo.services.exceptions.DatabaseException;
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

	@Transactional
	public AuthorDTO insert(AuthorDTO dto) {
		Author entity = new Author();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new AuthorDTO(entity);
	}
	
	@Transactional
	public AuthorDTO update(Long id, AuthorDTO dto) {
		try {
			Author entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new AuthorDTO(entity);
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
}
