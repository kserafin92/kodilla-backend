package com.kodilla.backendapp.controller;

import com.kodilla.backendapp.service.CurrencyConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CurrencyControllerTest {

    @Mock
    private CurrencyConversionService currencyConversionService;

    @InjectMocks
    private CurrencyController currencyController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @Test
    public void testGetAllReviews() throws Exception {
        // Given
        Map<String, Double> exchangeRates = Map.of(
                "USD", 4.20,
                "EUR", 4.50
        );

        // When
        when(currencyConversionService.getExchangeRates()).thenReturn(exchangeRates);

        // Then
        mockMvc.perform(get("/currency-rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.USD").value(4.20))
                .andExpect(jsonPath("$.EUR").value(4.50));
    }
}
