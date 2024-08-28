package com.kodilla.backendapp.mapper;

import com.kodilla.backendapp.domain.Doctor;
import com.kodilla.backendapp.domain.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorMapper {

    public DoctorDto toDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .build();
    }

    public Doctor toEntity(DoctorDto doctorDto) {
        return Doctor.builder()
                .id(doctorDto.getId())
                .firstName(doctorDto.getFirstName())
                .lastName(doctorDto.getLastName())
                .specialization(doctorDto.getSpecialization())
                .build();
    }
}
