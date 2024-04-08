package com.starbook.starbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starbook.starbook.model.Publisher;
import com.starbook.starbook.repository.PublisherRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PublisherService {
    
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
    	log.info("Find all publishers method started...");
        return publisherRepository.findAll();
    }

    public Publisher create(Publisher publisher) {
    	log.info("Create Publisher method started...");
        return publisherRepository.save(publisher);
    }

    public Optional<Publisher> findById(Long id) {
    	log.info("Find Publisher by Id method started...");
        return publisherRepository.findById(id);
    }

    public Optional<Publisher> update(Long id, Publisher updatedPublisher) {
    	log.info("update Publisher method started...");
        return publisherRepository.findById(id).map(publisher -> {
            publisher.setName(updatedPublisher.getName());
            publisher.setBooks(updatedPublisher.getBooks());
            return publisherRepository.save(publisher);
        });
    }

    public void delete(Long id) {
    	log.info("Delete Publisher method started...");
        publisherRepository.deleteById(id);
    }
}
