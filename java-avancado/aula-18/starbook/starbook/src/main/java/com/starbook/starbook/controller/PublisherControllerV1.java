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

import com.starbook.starbook.model.Publisher;
import com.starbook.starbook.service.PublisherServiceV1;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherControllerV1 {

    @Autowired
    @Qualifier("v1")
    private PublisherServiceV1 publisherService;
    
    @GetMapping("/sortedpublishers")
    public ResponseEntity<List<Publisher>> getAllPublishers(@RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            List<Publisher> publishers = publisherService.findAllSorted(sort);
            if (publishers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(publishers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Publisher>> getAllPublishers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Publisher> publisherPage = publisherService.findAll(PageRequest.of(page, size));
        if (publisherPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(publisherPage, HttpStatus.OK);
    }

    //Old method without pagination
	//    @GetMapping
	//    public ResponseEntity<List<Publisher>> getAllPublishers() {
	//        List<Publisher> publishers = publisherService.findAll();
	//        return ResponseEntity.ok(publishers);
	//    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        Optional<Publisher> publisher = publisherService.findById(id);
        return publisher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Publisher> getPublisherByName(@PathVariable String name) {
        Optional<Publisher> publisher = publisherService.findByName(name);
        return publisher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        Publisher createdPublisher = publisherService.create(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher updatedPublisher) {
        Optional<Publisher> publisher = publisherService.update(id, updatedPublisher);
        return publisher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
