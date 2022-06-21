package com.carlos.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlos.demo.entities.Author;
import com.carlos.demo.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
