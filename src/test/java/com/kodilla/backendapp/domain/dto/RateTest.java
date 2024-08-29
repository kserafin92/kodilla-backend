package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RateTest {

    @Test
    public void testGettersAndSetters() {
        Rate rate = new Rate();
        String no = "1";
        String effectiveDate = "2023-01-01";
        double mid = 3.75;

        rate.setNo(no);
        rate.setEffectiveDate(effectiveDate);
        rate.setMid(mid);

        assertEquals(no, rate.getNo());
        assertEquals(effectiveDate, rate.getEffectiveDate());
        assertEquals(mid, rate.getMid(), 0.001);
    }

    @Test
    public void testNoArgsConstructor() {
        Rate rate = new Rate();

        assertNull(rate.getNo());
        assertNull(rate.getEffectiveDate());
        assertEquals(0.0, rate.getMid(), 0.001);
    }

    @Test
    public void testAllArgsConstructor() {
        String no = "1";
        String effectiveDate = "2023-01-01";
        double mid = 3.75;

        Rate rate = new Rate(no, effectiveDate, mid);

        assertEquals(no, rate.getNo());
        assertEquals(effectiveDate, rate.getEffectiveDate());
        assertEquals(mid, rate.getMid(), 0.001);
    }

    @Test
    public void testBuilder() {
        String no = "1";
        String effectiveDate = "2023-01-01";
        double mid = 3.75;

        Rate rate = Rate.builder()
                .no(no)
                .effectiveDate(effectiveDate)
                .mid(mid)
                .build();

        assertEquals(no, rate.getNo());
        assertEquals(effectiveDate, rate.getEffectiveDate());
        assertEquals(mid, rate.getMid(), 0.001);
    }
}
