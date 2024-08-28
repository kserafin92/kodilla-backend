package com.kodilla.backendapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kodilla.backendapp.domain.Review;
import com.kodilla.backendapp.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register module for Java 8 Date and Time API
    }

    @Test
    void testGetAllReviews() throws Exception {
        Review review = Review.builder()
                .id(1L)
                .rating(5)
                .content("Excellent")
                .doctor(null) // You can replace null with a mock Doctor if needed
                .patient(null) // You can replace null with a mock Patient if needed
                .build();

        when(reviewService.findAll()).thenReturn(Collections.singletonList(review));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(review.getId()))
                .andExpect(jsonPath("$[0].rating").value(review.getRating()))
                .andExpect(jsonPath("$[0].content").value(review.getContent()))
                .andExpect(jsonPath("$[0].doctor").isEmpty()) // Adjust based on actual data
                .andExpect(jsonPath("$[0].patient").isEmpty()); // Adjust based on actual data
    }

    @Test
    void testGetReviewById() throws Exception {
        Review review = Review.builder()
                .id(1L)
                .rating(5)
                .content("Excellent")
                .doctor(null)
                .patient(null)
                .build();

        when(reviewService.findById(anyLong())).thenReturn(Optional.of(review));

        mockMvc.perform(get("/reviews/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(review.getId()))
                .andExpect(jsonPath("$.rating").value(review.getRating()))
                .andExpect(jsonPath("$.content").value(review.getContent()))
                .andExpect(jsonPath("$.doctor").isEmpty())
                .andExpect(jsonPath("$.patient").isEmpty());
    }

    @Test
    void testCreateReview() throws Exception {
        Review review = Review.builder()
                .rating(4)
                .content("Good")
                .doctor(null)
                .patient(null)
                .build();

        Review savedReview = Review.builder()
                .id(1L)
                .rating(4)
                .content("Good")
                .doctor(null)
                .patient(null)
                .build();

        when(reviewService.save(any(Review.class))).thenReturn(savedReview);

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedReview.getId()))
                .andExpect(jsonPath("$.rating").value(savedReview.getRating()))
                .andExpect(jsonPath("$.content").value(savedReview.getContent()))
                .andExpect(jsonPath("$.doctor").isEmpty())
                .andExpect(jsonPath("$.patient").isEmpty());
    }

    @Test
    void testUpdateReview() throws Exception {
        Review review = Review.builder()
                .id(1L)
                .rating(3)
                .content("Updated")
                .doctor(null)
                .patient(null)
                .build();

        when(reviewService.findById(anyLong())).thenReturn(Optional.of(review));
        when(reviewService.save(any(Review.class))).thenReturn(review);

        mockMvc.perform(put("/reviews/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(review.getId()))
                .andExpect(jsonPath("$.rating").value(review.getRating()))
                .andExpect(jsonPath("$.content").value(review.getContent()))
                .andExpect(jsonPath("$.doctor").isEmpty())
                .andExpect(jsonPath("$.patient").isEmpty());
    }

    @Test
    void testDeleteReview() throws Exception {
        when(reviewService.findById(anyLong())).thenReturn(Optional.of(new Review()));

        mockMvc.perform(delete("/reviews/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteReviewNotFound() throws Exception {
        when(reviewService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/reviews/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}
