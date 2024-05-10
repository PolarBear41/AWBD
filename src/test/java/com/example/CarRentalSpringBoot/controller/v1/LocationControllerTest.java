package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.service.impl.LocationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class LocationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LocationServiceImpl locationService;

    @InjectMocks
    private LocationController locationController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(locationController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void newLocation() throws Exception {
        LocationDto locationDto = new LocationDto("Suceava", "Strada Stefan cel Mare", new BigDecimal("47.637520"),
                new BigDecimal("26.259280"), "Suceava", "Suceava", "Romania", 1L);
        mockMvc.perform(post("/v1/location")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isCreated());

        verify(locationService, times(1)).newLocation(any(LocationDto.class));
    }

    @Test
    void getLocation() throws Exception {
        Long locationId = 1L;
        when(locationService.getLocation(locationId)).thenReturn(new LocationDto());

        mockMvc.perform(get("/v1/location/{locationId}", locationId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getAllLocations() throws Exception {
        when(locationService.getAllLocations(pageable)).thenReturn(Collections.singletonList(new LocationDto()));

        mockMvc.perform(get("/v1/location"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
