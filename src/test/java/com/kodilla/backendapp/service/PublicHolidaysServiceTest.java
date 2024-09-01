package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.dto.PublicHolidayDto;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublicHolidaysServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PublicHolidaysService publicHolidaysService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueWhenDateIsPublicHoliday() {
        // Given
        PublicHolidayDto[] holidaysArray = {
                new PublicHolidayDto("2025-01-01", "New Year's Day")
        };

        when(restTemplate.getForObject(anyString(), eq(PublicHolidayDto[].class)))
                .thenReturn(holidaysArray);

        // When
        boolean result = publicHolidaysService.isPublicHoliday(LocalDate.of(2025, 1, 1));

        // Then
        assertTrue(result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublicHolidayDto[].class));
    }

    @Test
    void shouldReturnFalseWhenDateIsNotPublicHoliday() {
        // Given
        PublicHolidayDto[] holidaysArray = {
                new PublicHolidayDto("2025-01-01", "New Year's Day")
        };

        when(restTemplate.getForObject(anyString(), eq(PublicHolidayDto[].class)))
                .thenReturn(holidaysArray);

        // When
        boolean result = publicHolidaysService.isPublicHoliday(LocalDate.of(2025, 2, 1));

        // Then
        assertFalse(result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublicHolidayDto[].class));
    }

    @Test
    void shouldThrowExceptionWhenNoHolidaysFound() {
        // Given
        when(restTemplate.getForObject(anyString(), eq(PublicHolidayDto[].class)))
                .thenReturn(new PublicHolidayDto[0]);

        // When & Then
        assertThrows(ResourceNotFoundException.class,
                () -> publicHolidaysService.isPublicHoliday(LocalDate.of(2025, 1, 1)));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublicHolidayDto[].class));
    }

    @Test
    void shouldThrowExceptionWhenApiReturnsNull() {
        // Given
        when(restTemplate.getForObject(anyString(), eq(PublicHolidayDto[].class)))
                .thenReturn(null);

        // When & Then
        assertThrows(ResourceNotFoundException.class,
                () -> publicHolidaysService.isPublicHoliday(LocalDate.of(2025, 1, 1)));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublicHolidayDto[].class));
    }
}
