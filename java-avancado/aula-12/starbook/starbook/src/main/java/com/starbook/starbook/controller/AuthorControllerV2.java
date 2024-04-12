package com.starbook.starbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.starbook.starbook.service.AuthorServiceV2;

@RestController
@RequestMapping("/api/v2/authors")
public class AuthorControllerV2 {
	
	@Autowired
    @Qualifier("authorServiceV2")
    private AuthorServiceV2 authorService;
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAllAuthors(){
		authorService.deleteAllAuthors();
		return ResponseEntity.noContent().build();
	}

}
