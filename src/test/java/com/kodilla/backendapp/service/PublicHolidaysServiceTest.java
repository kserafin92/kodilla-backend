package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.dto.PublicHolidayDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublicHolidaysServiceTest {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final PublicHolidaysService publicHolidaysService = new PublicHolidaysService(restTemplate);

    @Test
    void shouldReturnPublicHolidaysWhenApiCallIsSuccessful() {
        // When
        List<PublicHolidayDto> result = publicHolidaysService.getPublicHolidays();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("New Year's Day", result.get(0).getName());
    }
    @Test
    void shouldReturnTrueIfDateIsPublicHoliday() {



        LocalDate testDate = LocalDate.of(2024, 1, 2);

        // When
        boolean result = publicHolidaysService.isPublicHoliday(testDate);

        // Then
        assertFalse(result, "Expected the date to be a public holiday");
    }


}
