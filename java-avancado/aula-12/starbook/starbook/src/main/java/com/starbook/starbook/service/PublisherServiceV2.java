package com.starbook.starbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.starbook.starbook.repository.PublisherRepository;

@Service
@Qualifier("v2")
public class PublisherServiceV2 extends PublisherServiceV1 {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	public void deleteAllPublishers() {
		publisherRepository.deleteAll();
	}

}
