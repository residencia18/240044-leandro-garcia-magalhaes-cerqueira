package com.starbook.starbook.service;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.starbook.starbook.repository.PublisherRepository;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceV2Test {
	
	@Mock
	private PublisherRepository publisherRepository;
	
	@InjectMocks
	private PublisherServiceV2 publisherService;

	@Test
	void deleteAllPublishers_ShouldCallRepositoryDeleteAll() {
		
		publisherService.deleteAllPublishers();
		verify(publisherRepository).deleteAll();
	}
	
}