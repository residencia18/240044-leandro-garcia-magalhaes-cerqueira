package com.starbook.starbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.starbook.starbook.model.Author;
import com.starbook.starbook.repository.AuthorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary // To indicate what implementation must be prefer when Spring looking for inject a bean
@Qualifier("v1")
public class AuthorServiceV1 {
    
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
    	log.info("Find all authors method started...");
        return authorRepository.findAll();
        
    }

    public Author create(Author author) {
    	log.info("Create Author method started...");
        return authorRepository.save(author);
    }

    public Optional<Author> findById(Long id) {
    	log.info("Find Author by Id method started...");
        return authorRepository.findById(id);
    }
    
    public Optional<Author> findByName(String name) {
    	log.info("Find Author by Name method started...");
        return authorRepository.findByName(name);
    }

    public Optional<Author> update(Long id, Author updatedAuthor) {
    	log.info("update Author method started...");
        return authorRepository.findById(id).map(author -> {
            author.setName(updatedAuthor.getName());
            author.setBooks(updatedAuthor.getBooks());
            return authorRepository.save(author);
        });
    }

    public void delete(Long id) {
    	log.info("Delete Author method started...");
        authorRepository.deleteById(id);
    }
}
