package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment) {
        if (appointment == null || appointment.getDateTime() == null) {
            throw new InvalidDataException("Dane wizyty są nieprawidłowe");
        }
        return appointmentRepository.save(appointment);
    }
    public void deleteById(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Wizyta o id " + id + " nie została znaleziona");
        }
        appointmentRepository.deleteById(id);
    }
}
