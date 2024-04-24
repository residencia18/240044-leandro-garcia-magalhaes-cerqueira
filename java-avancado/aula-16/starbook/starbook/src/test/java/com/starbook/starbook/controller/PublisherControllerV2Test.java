package com.starbook.starbook.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.starbook.starbook.service.PublisherServiceV2;

@WebMvcTest(PublisherControllerV2.class)
public class PublisherControllerV2Test {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    @Qualifier("v2")
    private PublisherServiceV2 publisherService;

    @Test
    void deleteAllPublishers_ReturnsNoContentList() throws Exception { 
    	
    	mockMvc.perform(MockMvcRequestBuilders.delete("/api/v2/publishers"))
        .andExpect(status().isNoContent());
                        
    }
    

}
