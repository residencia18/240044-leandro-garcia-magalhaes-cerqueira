package com.starbook.starbook.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.starbook.starbook.service.UserServiceV2;


@WebMvcTest(UserControllerV2.class)
public class UserControllerV2Test {
	
	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private UserServiceV2 userService;
    
    @Test
    void deleteAllUsers_ReturnsNoContentList() throws Exception { 
    	
    	mockMvc.perform(MockMvcRequestBuilders.delete("/api/v2/users"))
        .andExpect(status().isNoContent());
                        
    }
      
}
