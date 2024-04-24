package com.starbook.starbook.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.starbook.starbook.service.BookServiceV2;

@WebMvcTest(BookControllerV2.class)
public class BookControllerV2Test {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    @Qualifier("v2")
    private BookServiceV2 bookService;

    @Test
    void deleteAllBooks_ReturnsNoContentList() throws Exception { 
    	
    	mockMvc.perform(MockMvcRequestBuilders.delete("/api/v2/books"))
        .andExpect(status().isNoContent());
                        
    }

}
