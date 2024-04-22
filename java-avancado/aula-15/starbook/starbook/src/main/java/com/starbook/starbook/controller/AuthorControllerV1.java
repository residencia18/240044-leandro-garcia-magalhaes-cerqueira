package com.starbook.starbook.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starbook.starbook.model.Author;
import com.starbook.starbook.service.AuthorServiceV1;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorControllerV1 {

    @Autowired
    @Qualifier("v1")
    private AuthorServiceV1 authorService;
    
    
    @GetMapping("/sortedauthors")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            List<Author> authors = authorService.findAllSorted(sort);
            if (authors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Author> authorPage = authorService.findAll(PageRequest.of(page, size));
        if (authorPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorPage, HttpStatus.OK);
    }


	// Old implementation, without pagination
	    
	//    @GetMapping
	//    public ResponseEntity<List<Author>> getAllAuthors() {
	//        List<Author> authors = authorService.findAll();
	//        return ResponseEntity.ok(authors);
	//    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name) {
        Optional<Author> author = authorService.findByName(name);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.create(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id,@RequestBody Author updatedAuthor) {
        Optional<Author> author = authorService.update(id, updatedAuthor);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
