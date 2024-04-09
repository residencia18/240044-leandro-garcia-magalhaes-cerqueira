package com.starbook.starbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.Publisher;
import com.starbook.starbook.repository.PublisherRepository;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {
	
	@Mock
	private PublisherRepository publisherRepository;
	
	@InjectMocks
	private PublisherService publisherService;

	private Faker faker;
	
	@BeforeEach
	void setUp() {
		faker = new Faker();
	}
	
	private Publisher generateFakePublisher() {
		Publisher publisher = new Publisher();
		publisher.setId(faker.number().randomNumber());
		publisher.setName(faker.name().fullName());
		return publisher;
	}
	
	@Test
	void createPublisher_WithValidData_ReturnPublisher() {
		Publisher fakePublisher = generateFakePublisher();
		given(publisherRepository.save(any(Publisher.class))).willReturn(fakePublisher);
		
		Publisher savedPublisher = publisherService.create(fakePublisher);
		
		assertNotNull(fakePublisher);
		assertEquals(fakePublisher.getId(), savedPublisher.getId());
		assertEquals(fakePublisher.getName(), savedPublisher.getName());
	
		verify(publisherRepository).save(any(Publisher.class));
		
	}
	
	@Test
	void deletePublisher_WithValidId_DeletesPublisher() {
	    // Create a fake publisher
	    Publisher fakePublisher = generateFakePublisher();

	    // Call the delete method of publisherService
	    publisherService.delete(fakePublisher.getId());

	    // Verify that the publisherRepository.delete method was called with the fake publisher
	    verify(publisherRepository).deleteById(fakePublisher.getId());
	}

	
	  @Test
	    void findPublisherById_WithUnexistingId_ReturnsEmpty() {
	        Long fakeId = faker.number().randomNumber();
	        given(publisherRepository.findById(fakeId)).willReturn(Optional.empty());

	        Optional<Publisher> result = publisherService.findById(fakeId);

	        assertFalse(result.isPresent());
	        verify(publisherRepository).findById(fakeId);
	   }
	      
	    @Test
	    void updatePublisher_WithValidData_ReturnsUpdatedPublisher() {
	        Publisher originalPublisher = generateFakePublisher();
	        Publisher updatedPublisher = generateFakePublisher();

	        given(publisherRepository.findById(originalPublisher.getId())).willReturn(Optional.of(originalPublisher));
	        given(publisherRepository.save(any(Publisher.class))).willReturn(updatedPublisher);

	        Optional<Publisher> result = publisherService.update(originalPublisher.getId(), updatedPublisher);

	        assertTrue(result.isPresent());
	        assertEquals(updatedPublisher.getName(), result.get().getName());
	        assertEquals(updatedPublisher.getId(), result.get().getId());
	        verify(publisherRepository).findById(originalPublisher.getId());
	        verify(publisherRepository).save(any(Publisher.class));
	    }
	
}