package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Doctor;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor save(Doctor doctor) {
        if (doctor == null || doctor.getFirstName() == null || doctor.getFirstName().isEmpty()) {
            throw new InvalidDataException("Dane doktora są nieprawidłowe");
        }
        return doctorRepository.save(doctor);
    }

    public void deleteById(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doktor o id " + id + " nie został znaleziony");
        }
        doctorRepository.deleteById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        if (doctor == null || doctor.getFirstName() == null || doctor.getLastName() == null) {
            throw new InvalidDataException("Dane doktora są nieprawidłowe");
        }
        return doctorRepository.save(doctor);
    }
}
