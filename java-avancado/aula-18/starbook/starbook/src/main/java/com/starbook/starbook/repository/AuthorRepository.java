package com.starbook.starbook.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbook.starbook.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {
	
	Optional<Author> findByName(String name);
	
	//Pagination
    Page<Author> findAll(Pageable pageable);
    Page<Author> findAllByNameContains(String name, Pageable pageable); 
   
}
