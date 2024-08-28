package com.kodilla.backendapp.mapper;

import com.kodilla.backendapp.domain.Review;
import com.kodilla.backendapp.domain.dto.DoctorDto;
import com.kodilla.backendapp.domain.dto.PatientDto;
import com.kodilla.backendapp.domain.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMapper {

    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .doctorId(review.getDoctor().getId())
                .patientId(review.getPatient().getId())
                .build();
    }

    public Review toEntity(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .rating(reviewDto.getRating())
                .content(reviewDto.getContent())
                .doctor(doctorMapper.toEntity(DoctorDto.builder().id(reviewDto.getDoctorId()).build()))
                .patient(patientMapper.toEntity(PatientDto.builder().id(reviewDto.getPatientId()).build()))
                .build();
    }
}
