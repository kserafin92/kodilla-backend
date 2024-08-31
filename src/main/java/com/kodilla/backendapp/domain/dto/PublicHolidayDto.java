package com.kodilla.backendapp.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicHolidayDto {

    private String date;
    private String name;
}


