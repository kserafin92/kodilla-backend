package com.kodilla.backendapp.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppointmentTest {

    @Test
    void testDefaultConstructor() {
        Appointment appointment = new Appointment();
        assertNotNull(appointment);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();

        Appointment appointment = new Appointment(1L, now, doctor, patient, 123.0, 123.0, "abc");

        assertEquals(1L, appointment.getId());
        assertEquals(now, appointment.getDateTime());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void testBuilder() {
        LocalDateTime now = LocalDateTime.now();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();

        Appointment appointment = Appointment.builder()
                .id(1L)
                .dateTime(now)
                .doctor(doctor)
                .patient(patient)
                .build();

        assertEquals(1L, appointment.getId());
        assertEquals(now, appointment.getDateTime());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void testSettersAndGetters() {
        Appointment appointment = new Appointment();
        LocalDateTime now = LocalDateTime.now();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();

        appointment.setId(1L);
        appointment.setDateTime(now);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        assertEquals(1L, appointment.getId());
        assertEquals(now, appointment.getDateTime());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(patient, appointment.getPatient());
    }
}
