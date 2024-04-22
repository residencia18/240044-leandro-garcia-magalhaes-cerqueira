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

import com.starbook.starbook.model.Book;
import com.starbook.starbook.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
@Qualifier("v1")
public class BookServiceV1 {
    
    @Autowired
    private BookRepository bookRepository;
    
    
    @Cacheable("booksSorted")
    public List<Book> findAllSorted(String[] sort) {
        List<Order> orders = new ArrayList<>();
        for (String sortOrder : sort) {
            String[] _sort = sortOrder.split(",");
            orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
        }
        return bookRepository.findAll(Sort.by(orders));
    }
    
    private Sort.Direction getSortDirection(String direction) {
        if ("asc".equals(direction)) {
            return Sort.Direction.ASC;
        } else if ("desc".equals(direction)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @Cacheable("booksPageables")
    public Page<Book> findAll(Pageable pageable) {
    	log.info("Find all books method started...");
        return bookRepository.findAll(pageable);
        
    }
    
    @Cacheable("books")
    public List<Book> findAll() {
    	log.info("Find all books method started...");
        return bookRepository.findAll();
    }

    public Book create(Book book) {
    	log.info("Create Book method started...");
        return bookRepository.save(book);
        
    }

    @Cacheable("bookById")
    public Optional<Book> findById(Long id) {
    	log.info("Find Book by Id method started...");
        return bookRepository.findById(id);
    }
  
    @Cacheable("bookByTitle")
    public Optional<Book> findByTitle(String title) {
    	log.info("Find Book by title method started...");
        return bookRepository.findByTitle(title);
    }

    public Optional<Book> update(Long id, Book updatedBook) {
    	log.info("update Book method started...");
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setGenre(updatedBook.getGenre());
            book.setSubgenre(updatedBook.getSubgenre());
            book.setPublication_date(updatedBook.getPublication_date());
            book.setPage_count(updatedBook.getPage_count());
            book.setStars(updatedBook.getStars());
            book.setReview(updatedBook.getReview());
            book.setCover(updatedBook.getCover());
            book.setPhysical(updatedBook.isPhysical());
            book.setPublisher(updatedBook.getPublisher());
            book.setAuthors(updatedBook.getAuthors());
            book.setUsers(updatedBook.getUsers());
            return bookRepository.save(book);
        });
    }

    public void delete(Long id) {
    	log.info("Delete Book method started...");
        bookRepository.deleteById(id);
    }
}
