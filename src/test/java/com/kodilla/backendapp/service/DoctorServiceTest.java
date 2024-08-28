package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.domain.Doctor;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.AppointmentRepository;
import com.kodilla.backendapp.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    void testFindAll() {
        Doctor doctor = new Doctor();
        when(doctorRepository.findAll()).thenReturn(Collections.singletonList(doctor));

        List<Doctor> doctors = doctorService.findAll();
        assertEquals(1, doctors.size());
    }

    @Test
    void testFindById() {
        Doctor doctor = new Doctor();
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));

        Optional<Doctor> foundDoctor = doctorService.findById(1L);
        assertEquals(doctor, foundDoctor.orElse(null));
    }

    @Test
    void testSave() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Jan");
        doctor.setLastName("Kowalski");
        doctor.setSpecialization("Kardiolog");

        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Doctor savedDoctor = doctorService.save(doctor);
        assertEquals(doctor, savedDoctor);
    }

    @Test
    void testSaveWithInvalidData() {
        Doctor doctor = new Doctor();
        assertThrows(InvalidDataException.class, () -> doctorService.save(doctor));
    }



    @Test
    void testDeleteByIdWhenNotFound() {
        when(doctorRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> doctorService.deleteById(1L));
    }

    @Test
    void testDeleteDoctorRemovesAppointments() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setLastName("Smith");
        doctor.setSpecialization("Cardiology");

        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setDoctor(doctor);

        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setDoctor(doctor);

        doctor.setAppointments(new ArrayList<>(List.of(appointment1, appointment2)));

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        doctorService.deleteById(1L);

        verify(appointmentRepository).deleteById(1L);
        verify(appointmentRepository).deleteById(2L);
    }

}
