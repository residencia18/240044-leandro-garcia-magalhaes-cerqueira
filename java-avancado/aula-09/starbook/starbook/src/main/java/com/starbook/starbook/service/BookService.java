package com.starbook.starbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starbook.starbook.model.Book;
import com.starbook.starbook.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
    	log.info("Find all books method started...");
        return bookRepository.findAll();
    }

    public Book create(Book book) {
    	log.info("Create Book method started...");
        return bookRepository.save(book);
        
    }

    public Optional<Book> findById(Long id) {
    	log.info("Find Book by Id method started...");
        return bookRepository.findById(id);
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
