package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NbpApiResponseTest {

    @Test
    public void testGettersAndSetters() {
        NbpApiResponse response = new NbpApiResponse();
        String table = "A";
        String currency = "USD";
        String code = "USD";
        List<Rate> rates = List.of(new Rate("1", "2023-01-01", 3.75));

        response.setTable(table);
        response.setCurrency(currency);
        response.setCode(code);
        response.setRates(rates);

        assertEquals(table, response.getTable());
        assertEquals(currency, response.getCurrency());
        assertEquals(code, response.getCode());
        assertEquals(rates, response.getRates());
    }

    @Test
    public void testNoArgsConstructor() {
        NbpApiResponse response = new NbpApiResponse();

        assertNull(response.getTable());
        assertNull(response.getCurrency());
        assertNull(response.getCode());
        assertNull(response.getRates());
    }

    @Test
    public void testAllArgsConstructor() {
        String table = "A";
        String currency = "USD";
        String code = "USD";
        List<Rate> rates = List.of(new Rate("1", "2023-01-01", 3.75));

        NbpApiResponse response = new NbpApiResponse(table, currency, code, rates);

        assertEquals(table, response.getTable());
        assertEquals(currency, response.getCurrency());
        assertEquals(code, response.getCode());
        assertEquals(rates, response.getRates());
    }

    @Test
    public void testBuilder() {
        String table = "A";
        String currency = "USD";
        String code = "USD";
        List<Rate> rates = List.of(new Rate("1", "2023-01-01", 3.75));

        NbpApiResponse response = NbpApiResponse.builder()
                .table(table)
                .currency(currency)
                .code(code)
                .rates(rates)
                .build();

        assertEquals(table, response.getTable());
        assertEquals(currency, response.getCurrency());
        assertEquals(code, response.getCode());
        assertEquals(rates, response.getRates());
    }
}
