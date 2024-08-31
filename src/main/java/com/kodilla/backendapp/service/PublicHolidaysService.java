package com.kodilla.backendapp.service;


import com.kodilla.backendapp.domain.dto.PublicHolidayDto;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PublicHolidaysService {


    private static final String PH_API_URL = "https://date.nager.at/api/v3/PublicHolidays/{year}/PL";
    private final RestTemplate restTemplate;

    List<PublicHolidayDto> getPublicHolidays() {
        String url = PH_API_URL.replace("{year}", String.valueOf(LocalDate.now().getYear()));
        PublicHolidayDto[] response = restTemplate.getForObject(url, PublicHolidayDto[].class);

        if (response != null && response.length>0) {
            return Arrays.asList(response);
        }

        throw new ResourceNotFoundException("Nie udało się pobrać świąt");
    }

    public boolean isPublicHoliday(LocalDate date){
        List<PublicHolidayDto> publicHolidays =getPublicHolidays();
        String formattedDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return publicHolidays.stream()
                .anyMatch(holiday -> holiday.getDate().equals(formattedDate));
    }
}




