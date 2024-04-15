package com.starbook.starbook.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbook.starbook.model.Book;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {
	
	Optional<Book> findByTitle(String title);
	
	//Pagination
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByTitleContains(String title, Pageable pageable); 
    Page<Book> findByIsPhysical(boolean isPhysical, Pageable pageable);
    Page<Book> findByGenre(String genre, Pageable pageable);
    Page<Book> findBySubgenre(String subgenre, Pageable pageable);
	
}
