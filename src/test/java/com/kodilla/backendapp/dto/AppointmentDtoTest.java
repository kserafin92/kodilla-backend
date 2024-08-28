package com.kodilla.backendapp.dto;

import com.kodilla.backendapp.domain.dto.AppointmentDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppointmentDtoTest {

    @Test
    void testDefaultConstructor() {
        AppointmentDto appointmentDto = new AppointmentDto();
        assertNotNull(appointmentDto);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        AppointmentDto appointmentDto = new AppointmentDto(1L, now, 2L, 3L, 123.1);

        assertEquals(1L, appointmentDto.getId());
        assertEquals(now, appointmentDto.getDateTime());
        assertEquals(2L, appointmentDto.getDoctorId());
        assertEquals(3L, appointmentDto.getPatientId());
    }

    @Test
    void testBuilder() {
        LocalDateTime now = LocalDateTime.now();
        AppointmentDto appointmentDto = AppointmentDto.builder()
                .id(1L)
                .dateTime(now)
                .doctorId(2L)
                .patientId(3L)
                .build();

        assertEquals(1L, appointmentDto.getId());
        assertEquals(now, appointmentDto.getDateTime());
        assertEquals(2L, appointmentDto.getDoctorId());
        assertEquals(3L, appointmentDto.getPatientId());
    }

    @Test
    void testSettersAndGetters() {
        AppointmentDto appointmentDto = new AppointmentDto();
        LocalDateTime now = LocalDateTime.now();

        appointmentDto.setId(1L);
        appointmentDto.setDateTime(now);
        appointmentDto.setDoctorId(2L);
        appointmentDto.setPatientId(3L);

        assertEquals(1L, appointmentDto.getId());
        assertEquals(now, appointmentDto.getDateTime());
        assertEquals(2L, appointmentDto.getDoctorId());
        assertEquals(3L, appointmentDto.getPatientId());
    }
}
