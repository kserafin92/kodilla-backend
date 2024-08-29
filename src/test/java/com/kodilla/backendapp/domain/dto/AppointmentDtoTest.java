package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentDtoTest {

    @Test
    public void testGettersAndSetters() {
        AppointmentDto appointment = new AppointmentDto();
        Long id = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        Long doctorId = 2L;
        Long patientId = 3L;
        Double price = 200.0;

        appointment.setId(id);
        appointment.setDateTime(dateTime);
        appointment.setDoctorId(doctorId);
        appointment.setPatientId(patientId);
        appointment.setPrice(price);

        assertEquals(id, appointment.getId());
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals(doctorId, appointment.getDoctorId());
        assertEquals(patientId, appointment.getPatientId());
        assertEquals(price, appointment.getPrice());
    }

    @Test
    public void testNoArgsConstructor() {
        AppointmentDto appointment = new AppointmentDto();

        assertNull(appointment.getId());
        assertNull(appointment.getDateTime());
        assertNull(appointment.getDoctorId());
        assertNull(appointment.getPatientId());
        assertNull(appointment.getPrice());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        Long doctorId = 2L;
        Long patientId = 3L;
        Double price = 200.0;

        AppointmentDto appointment = new AppointmentDto(id, dateTime, doctorId, patientId, price);

        assertEquals(id, appointment.getId());
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals(doctorId, appointment.getDoctorId());
        assertEquals(patientId, appointment.getPatientId());
        assertEquals(price, appointment.getPrice());
    }

    @Test
    public void testBuilder() {
        Long id = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        Long doctorId = 2L;
        Long patientId = 3L;
        Double price = 200.0;

        AppointmentDto appointment = AppointmentDto.builder()
                .id(id)
                .dateTime(dateTime)
                .doctorId(doctorId)
                .patientId(patientId)
                .price(price)
                .build();

        assertEquals(id, appointment.getId());
        assertEquals(dateTime, appointment.getDateTime());
        assertEquals(doctorId, appointment.getDoctorId());
        assertEquals(patientId, appointment.getPatientId());
        assertEquals(price, appointment.getPrice());
    }
}
