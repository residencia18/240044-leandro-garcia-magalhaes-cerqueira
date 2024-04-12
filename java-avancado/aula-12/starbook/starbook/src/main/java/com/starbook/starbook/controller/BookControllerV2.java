package com.starbook.starbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starbook.starbook.service.BookServiceV2;

@RestController
@RequestMapping("/api/v2/books")
public class BookControllerV2 {
	
	@Autowired
    @Qualifier("bookServiceV2")
    private BookServiceV2 bookService;
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAllBooks(){
		bookService.deleteAllBooks();
		return ResponseEntity.noContent().build();
	}

}
