package com.example.demo;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.controller.ApiController;
import com.example.demo.controller.ApiRestController;
import com.example.demo.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

    private MockMvc mockMvc;

    @InjectMocks
    private ApiController apiController;

    @InjectMocks
    private ApiRestController apiRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController, apiRestController).build();
    }

    @Test
    void testRepairBayRedirectsWhenDamagedSystemIsEmpty() throws Exception {
        try (var mockedService = mockStatic(Service.class)) {
            when(Service.getDamagedSystem()).thenReturn(""); // Simulate no damaged system

            mockMvc.perform(get("/repair-bay"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/update"));
        }
    }

    @Test
    void testRepairBayLoadsCorrectly() throws Exception {
        try (var mockedService = mockStatic(Service.class)) {
            when(Service.getDamagedSystem()).thenReturn("navigation");
            when(Service.getSystemCodes()).thenReturn(Map.of("navigation", "NAV-01"));

            mockMvc.perform(get("/repair-bay"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("systemCode", "NAV-01"))
                    .andExpect(view().name("index"));
        }
    }

    @Test
    void testUpdateSystemPost() throws Exception {
        try (var mockedService = mockStatic(Service.class)) {
            when(Service.getSystemCodes()).thenReturn(Map.of("navigation", "NAV-01"));

            mockMvc.perform(post("/update-system").param("system", "navigation"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/repair-bay"));
        }
    }

    @Test
    void testGetStatus() throws Exception {
        try (var mockedService = mockStatic(Service.class)) {
            when(Service.getDamagedSystem()).thenReturn("navigation");

            mockMvc.perform(get("/status"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.damagedSystem").value("navigation"));
        }
    }

    @Test
    void testTeapotResponse() throws Exception {
        mockMvc.perform(get("/teapot"))
                .andExpect(status().is(HttpStatus.I_AM_A_TEAPOT.value()));
    }
}