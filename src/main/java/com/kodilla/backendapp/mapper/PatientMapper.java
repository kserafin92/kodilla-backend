package com.kodilla.backendapp.mapper;

import com.kodilla.backendapp.domain.Patient;
import com.kodilla.backendapp.domain.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientMapper {

    public PatientDto toDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .build();
    }

    public Patient toEntity(PatientDto patientDto) {
        return Patient.builder()
                .id(patientDto.getId())
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .email(patientDto.getEmail())
                .phone(patientDto.getPhone())
                .build();
    }
}
