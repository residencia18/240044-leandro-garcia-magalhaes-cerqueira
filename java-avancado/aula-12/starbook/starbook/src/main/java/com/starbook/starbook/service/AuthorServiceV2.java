package com.starbook.starbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.starbook.starbook.repository.AuthorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("v2")
public class AuthorServiceV2 extends AuthorServiceV1 {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public void deleteAllAuthors() {
		log.info("Agressive method (Deleting All Authors) started...");
		authorRepository.deleteAll();
	}

}
