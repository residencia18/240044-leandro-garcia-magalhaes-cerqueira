package com.starbook.starbook.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
    
    @Cacheable("authorsSorted")
    public List<Author> findAllSorted(String[] sort) {
        List<Order> orders = new ArrayList<>();
        for (String sortOrder : sort) {
            String[] _sort = sortOrder.split(",");
            orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
        }
        return authorRepository.findAll(Sort.by(orders));
    }
    
    private Sort.Direction getSortDirection(String direction) {
        if ("asc".equals(direction)) {
            return Sort.Direction.ASC;
        } else if ("desc".equals(direction)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @Cacheable("authorsPageables")
    public Page<Author> findAll(Pageable pageable) {
    	log.info("Find all authors method started...");
        return authorRepository.findAll(pageable);
        
    }
    
    
    @Cacheable("authors")
    public List<Author> findAll() {
    	log.info("Find all books method started...");
        return authorRepository.findAll();
    }

    public Author create(Author author) {
    	log.info("Create Author method started...");
        return authorRepository.save(author);
    }

    @Cacheable("authorById")
    public Optional<Author> findById(Long id) {
    	log.info("Find Author by Id method started...");
        return authorRepository.findById(id);
    }
    
    @Cacheable("authorByName")
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
