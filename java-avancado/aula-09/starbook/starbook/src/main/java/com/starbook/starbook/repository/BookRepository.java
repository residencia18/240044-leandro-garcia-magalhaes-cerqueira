package com.starbook.starbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.starbook.starbook.model.Book;

public interface BookRepository extends JpaRepository <Book, Long> {
	
	

}
