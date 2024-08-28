package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Patient;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Patient patient = new Patient();
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        List<Patient> patients = patientService.findAll();

        assertNotNull(patients);
        assertEquals(1, patients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Patient patient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(patient, result.get());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveValidPatient() {
        Patient patient = new Patient();
        patient.setEmail("valid@example.com");
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.save(patient);

        assertNotNull(savedPatient);
        assertEquals("valid@example.com", savedPatient.getEmail());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testSaveInvalidPatient() {
        Patient patient = new Patient();
        patient.setEmail("");  // Invalid email

        InvalidDataException thrown = assertThrows(InvalidDataException.class, () -> patientService.save(patient));
        assertEquals("Dane pacjenta są nieprawidłowe", thrown.getMessage());
        verify(patientRepository, times(0)).save(patient);
    }

    @Test
    void testDeleteByIdSuccess() {
        when(patientRepository.existsById(1L)).thenReturn(true);

        patientService.deleteById(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(patientRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> patientService.deleteById(1L));
        assertEquals("Pacjent o id 1 nie został znaleziony", thrown.getMessage());
        verify(patientRepository, times(0)).deleteById(1L);
    }
}
