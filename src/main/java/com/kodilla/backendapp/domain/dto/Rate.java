package com.kodilla.backendapp.domain.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class Rate {
    private String no;
    private String effectiveDate;
    private double mid;
}
