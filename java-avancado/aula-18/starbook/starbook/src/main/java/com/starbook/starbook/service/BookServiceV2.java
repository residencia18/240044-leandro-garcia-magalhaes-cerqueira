package com.starbook.starbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.starbook.starbook.repository.BookRepository;

@Service
@Qualifier("v2")
public class BookServiceV2 extends BookServiceV1 {

	@Autowired
	private BookRepository bookRepository;
	
	public void deleteAllBooks() {
		bookRepository.deleteAll();
	}
	
	

}
