package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Review;
import com.kodilla.backendapp.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    void testFindAll() {
        Review review = new Review();
        when(reviewRepository.findAll()).thenReturn(Collections.singletonList(review));

        List<Review> reviews = reviewService.findAll();
        assertEquals(1, reviews.size());
    }

    @Test
    void testFindById() {
        Review review = new Review();
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));

        Optional<Review> foundReview = reviewService.findById(1L);
        assertEquals(review, foundReview.orElse(null));
    }

    @Test
    void testSave() {
        Review review = new Review();
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review savedReview = reviewService.save(review);
        assertEquals(review, savedReview);
    }

    @Test
    void testDeleteById() {
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(new Review()));
        reviewService.deleteById(1L);
        verify(reviewRepository).deleteById(1L);
    }
}
