package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.domain.Doctor;
import com.kodilla.backendapp.domain.Patient;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionIfAppointmentOutsideWorkingHours() {
        // Given
        LocalDateTime invalidTime = LocalDateTime.of(2024, 8, 30, 18, 0);
        Appointment appointment = new Appointment(1L, invalidTime, new Doctor(), new Patient(), 100.0, 430.0, "USD");

        // When & Then
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> appointmentService.save(appointment));
        assertEquals("Wizyty mogą być zaplanowane tylko między 7:00 a 16:00.", exception.getMessage());
    }


    @Test
    void shouldAllowAppointmentDuringWorkingHours() {
        // Given
        LocalDateTime validTime = LocalDateTime.of(2024, 8, 30, 10, 0);
        Appointment appointment = new Appointment(1L, validTime, new Doctor(), new Patient(), 100.0, 430.0, "USD");

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // When
        Appointment savedAppointment = appointmentService.save(appointment);

        // Then
        assertNotNull(savedAppointment);
        assertEquals(validTime, savedAppointment.getDateTime());
    }
}
