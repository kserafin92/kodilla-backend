package com.kodilla.backendapp.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class NbpApiResponse {
    private String table;
    private String currency;
    private String code;
    private java.util.List<Rate> rates;
}