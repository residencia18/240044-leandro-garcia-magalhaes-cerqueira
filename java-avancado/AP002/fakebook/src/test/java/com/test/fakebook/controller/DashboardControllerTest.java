package com.test.fakebook.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {

    @Mock
    private SecurityContextHolder securityContextHolder;

    @InjectMocks
    private DashboardController dashboardController;

    @Test
    public void testDashboard_Unauthenticated() throws Exception {
        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();

        // Perform GET request to dashboard endpoint
        mockMvc.perform(get("/api/dashboard"))
                // Verify that the status is OK
                .andExpect(status().isOk())
                // Verify that the response content is correct
                .andExpect(content().string("Hello Guest"));
    }


}

