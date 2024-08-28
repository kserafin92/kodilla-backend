package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.dto.NbpApiResponse;
import com.kodilla.backendapp.domain.dto.Rate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CurrencyConversionServiceTest {

    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    private final CurrencyConversionService service = new CurrencyConversionService(restTemplate);

    @Test
    void testGetExchangeRates() {

        NbpApiResponse responseEUR = new NbpApiResponse();
        responseEUR.setRates(Collections.singletonList(new Rate("123", "2024-08-25", 4.5)));
        Mockito.when(restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/EUR/?format=json", NbpApiResponse.class))
                .thenReturn(responseEUR);


        NbpApiResponse responseUSD = new NbpApiResponse();
        responseUSD.setRates(Collections.singletonList(new Rate("124", "2024-08-25", 3.8)));
        Mockito.when(restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/USD/?format=json", NbpApiResponse.class))
                .thenReturn(responseUSD);


        NbpApiResponse responseCHF = new NbpApiResponse();
        responseCHF.setRates(Collections.singletonList(new Rate("125", "2024-08-25", 4.0)));
        Mockito.when(restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/CHF/?format=json", NbpApiResponse.class))
                .thenReturn(responseCHF);


        NbpApiResponse responseGBP = new NbpApiResponse();
        responseGBP.setRates(Collections.singletonList(new Rate("126", "2024-08-25", 5.0)));
        Mockito.when(restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/GBP/?format=json", NbpApiResponse.class))
                .thenReturn(responseGBP);


        Map<String, Double> rates = service.getExchangeRates();


        Map<String, Double> expectedRates = new HashMap<>();
        expectedRates.put("EUR", 4.5);
        expectedRates.put("USD", 3.8);
        expectedRates.put("CHF", 4.0);
        expectedRates.put("GBP", 5.0);


        assertEquals(expectedRates, rates);
    }
}
