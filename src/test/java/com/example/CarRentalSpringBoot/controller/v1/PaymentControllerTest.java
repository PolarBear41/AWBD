package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.controller.v1.PaymentController;
import com.example.CarRentalSpringBoot.pojo.dto.PaymentDto;
import com.example.CarRentalSpringBoot.service.impl.PaymentServiceImpl;
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
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class PaymentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void makePayment() throws Exception {
        PaymentDto paymentDto = new PaymentDto(Date.from(java.time.Instant.now()), new BigDecimal("100.00"), "Cash", 1L);

        mockMvc.perform(post("/v1/payment")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isCreated());

        verify(paymentService, times(1)).makePayment(any(PaymentDto.class));
    }

    @Test
    void getPayment() throws Exception {
        Long paymentId = 1L;
        when(paymentService.getPaymentBasedOnId(paymentId)).thenReturn(new PaymentDto());

        mockMvc.perform(get("/v1/payment/{paymentId}", paymentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getAllPayments() throws Exception {
        when(paymentService.getAllPayments(pageable)).thenReturn(Collections.singletonList(new PaymentDto()));

        mockMvc.perform(get("/v1/payment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}

