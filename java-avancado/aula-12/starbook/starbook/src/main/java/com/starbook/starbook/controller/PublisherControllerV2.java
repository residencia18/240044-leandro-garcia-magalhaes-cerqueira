package com.starbook.starbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starbook.starbook.service.PublisherServiceV2;

@RestController
@RequestMapping("/api/v2/publishers")
public class PublisherControllerV2 {
	
	@Autowired
    @Qualifier("publisherServiceV2")
    private PublisherServiceV2 publisherService;
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAllPublishers(){
		publisherService.deleteAllPublishers();
		return ResponseEntity.noContent().build();
	}

}