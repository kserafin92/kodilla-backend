package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.domain.Reminder;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final EmailService emailService;

    public List<Reminder> findAll() {
        return reminderRepository.findAll();
    }

    public Reminder save(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public void deleteById(Long id) {
        if (!reminderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Przypomnienie o id " + id + " nie zostało znalezione");
        }
        reminderRepository.deleteById(id);
    }

    public List<Reminder> findByAppointmentDate(LocalDateTime date) {
        return reminderRepository.findByAppointment_DateTime(date);
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendRemindersForTomorrowAppointments() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay();

        List<Reminder> reminders = reminderRepository.findByAppointment_DateTime(tomorrow);

        for (Reminder reminder : reminders) {
            Appointment appointment = reminder.getAppointment();
            if (appointment.getPatient() == null || appointment.getPatient().getEmail() == null) {
                throw new ResourceNotFoundException("Nie można wysłać przypomnienia, brak danych pacjenta lub adresu email.");
            }
            String email = appointment.getPatient().getEmail();
            String subject = "Przypomnienie o jutrzejszej wizycie";
            String text = "Pamiętaj o jutrzejszej wizycie";

            emailService.sendEmail(email, subject, text);
        }
    }
}
