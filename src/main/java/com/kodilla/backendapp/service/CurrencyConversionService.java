package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.dto.NbpApiResponse;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/{currency}/?format=json";
    private final RestTemplate restTemplate;

    public Map<String, Double> getExchangeRates() {
        Map<String, Double> conversionRates = new HashMap<>();
        conversionRates.put("EUR", getExchangeRate("EUR"));
        conversionRates.put("USD", getExchangeRate("USD"));
        conversionRates.put("CHF", getExchangeRate("CHF"));
        conversionRates.put("GBP", getExchangeRate("GBP"));

        return conversionRates;
    }

    private double getExchangeRate(String currencyCode) {
        String url = NBP_API_URL.replace("{currency}", currencyCode);
        NbpApiResponse response = restTemplate.getForObject(url, NbpApiResponse.class);

        if (response != null && !response.getRates().isEmpty()) {
            return response.getRates().get(0).getMid();
        }

        throw new ResourceNotFoundException("Nie udało się pobrać kursu waluty dla " + currencyCode);
    }
}
