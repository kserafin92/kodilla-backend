package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewDtoTest {

    @Test
    public void testGettersAndSetters() {
        ReviewDto review = new ReviewDto();
        Long id = 1L;
        int rating = 5;
        String content = "Great doctor!";
        Long doctorId = 2L;
        Long patientId = 3L;

        review.setId(id);
        review.setRating(rating);
        review.setContent(content);
        review.setDoctorId(doctorId);
        review.setPatientId(patientId);

        assertEquals(id, review.getId());
        assertEquals(rating, review.getRating());
        assertEquals(content, review.getContent());
        assertEquals(doctorId, review.getDoctorId());
        assertEquals(patientId, review.getPatientId());
    }

    @Test
    public void testNoArgsConstructor() {
        ReviewDto review = new ReviewDto();

        assertNull(review.getId());
        assertEquals(0, review.getRating());
        assertNull(review.getContent());
        assertNull(review.getDoctorId());
        assertNull(review.getPatientId());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        int rating = 5;
        String content = "Great doctor!";
        Long doctorId = 2L;
        Long patientId = 3L;

        ReviewDto review = new ReviewDto(id, rating, content, doctorId, patientId);

        assertEquals(id, review.getId());
        assertEquals(rating, review.getRating());
        assertEquals(content, review.getContent());
        assertEquals(doctorId, review.getDoctorId());
        assertEquals(patientId, review.getPatientId());
    }

    @Test
    public void testBuilder() {
        Long id = 1L;
        int rating = 5;
        String content = "Great doctor!";
        Long doctorId = 2L;
        Long patientId = 3L;

        ReviewDto review = ReviewDto.builder()
                .id(id)
                .rating(rating)
                .content(content)
                .doctorId(doctorId)
                .patientId(patientId)
                .build();

        assertEquals(id, review.getId());
        assertEquals(rating, review.getRating());
        assertEquals(content, review.getContent());
        assertEquals(doctorId, review.getDoctorId());
        assertEquals(patientId, review.getPatientId());
    }
}
