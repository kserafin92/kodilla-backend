package com.kodilla.backendapp.mapper;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.domain.dto.AppointmentDto;
import com.kodilla.backendapp.domain.dto.DoctorDto;
import com.kodilla.backendapp.domain.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentMapper {

    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public AppointmentDto toDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .dateTime(appointment.getDateTime())
                .doctorId(appointment.getDoctor().getId())
                .patientId(appointment.getPatient().getId())
                .build();
    }

    public Appointment toEntity(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .id(appointmentDto.getId())
                .dateTime(appointmentDto.getDateTime())
                .doctor(doctorMapper.toEntity(DoctorDto.builder().id(appointmentDto.getDoctorId()).build()))
                .patient(patientMapper.toEntity(PatientDto.builder().id(appointmentDto.getPatientId()).build()))
                .build();
    }
}
