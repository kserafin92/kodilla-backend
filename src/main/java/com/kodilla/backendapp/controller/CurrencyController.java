package com.kodilla.backendapp.controller;

import com.kodilla.backendapp.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/currency-rates")
public class CurrencyController {
    @Autowired
    private CurrencyConversionService currencyConversionService;
    @GetMapping
    public Map<String, Double> getAllReviews() {
        return currencyConversionService.getExchangeRates();
    }
}