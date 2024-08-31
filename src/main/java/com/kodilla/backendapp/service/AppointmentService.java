package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.exception.InvalidDataException;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PublicHolidaysService publicHolidaysService;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment) {
        validateAppointmentTime(appointment.getDateTime());
        if (appointmentRepository.findByDoctor_IdAndDateTime(appointment.getDoctor().getId(), appointment.getDateTime()).isPresent()) {
            throw new IllegalArgumentException("Nie można utworzyć dwóch wizyt u tego samego doktora w tym samym czasie.");
        }


        return appointmentRepository.save(appointment);
    }
    public void deleteById(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Wizyta o id " + id + " nie została znaleziona");
        }
        appointmentRepository.deleteById(id);
    }
    private void validateAppointmentTime(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        if (hour < 7 || hour >= 16) {
            throw new InvalidDataException("Wizyty mogą być zaplanowane tylko między 7:00 a 16:00.");
        }
        if (publicHolidaysService.isPublicHoliday(dateTime.toLocalDate())){
            throw new InvalidDataException("Święto, nie można rejerstrować wizyt");
        }
    }
}
