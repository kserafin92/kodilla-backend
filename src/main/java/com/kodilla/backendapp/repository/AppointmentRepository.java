package com.kodilla.backendapp.repository;

import com.kodilla.backendapp.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByDoctor_IdAndDateTime(Long doctorId, LocalDateTime dateTime);
}
