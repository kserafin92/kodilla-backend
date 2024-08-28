package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Patient;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient save(Patient patient) {
        if (patient == null || patient.getEmail() == null || patient.getEmail().isEmpty()) {
            throw new InvalidDataException("Dane pacjenta są nieprawidłowe");
        }
        return patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pacjent o id " + id + " nie został znaleziony");
        }
        patientRepository.deleteById(id);
    }
}
