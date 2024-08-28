package com.kodilla.backendapp.service;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.domain.Patient;
import com.kodilla.backendapp.domain.Reminder;
import com.kodilla.backendapp.exception.ResourceNotFoundException;
import com.kodilla.backendapp.repository.ReminderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReminderServiceTest {

    @InjectMocks
    private ReminderService reminderService;

    @Mock
    private ReminderRepository reminderRepository;

    @Mock
    private EmailService emailService;

    @Test
    void testFindAll() {
        Reminder reminder = new Reminder();
        when(reminderRepository.findAll()).thenReturn(Collections.singletonList(reminder));

        List<Reminder> reminders = reminderService.findAll();
        assertEquals(1, reminders.size());
    }

    @Test
    void testSave() {
        Reminder reminder = new Reminder();
        when(reminderRepository.save(any(Reminder.class))).thenReturn(reminder);

        Reminder savedReminder = reminderService.save(reminder);
        assertEquals(reminder, savedReminder);
    }

    @Test
    void testDeleteById() {

        when(reminderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reminderService.deleteById(1L));

        verify(reminderRepository, never()).deleteById(anyLong());
    }


    @Test
    void testSendRemindersForTomorrowAppointments() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay();
        Reminder reminder = new Reminder();
        Appointment appointment = new Appointment();

        Patient patient = new Patient();
        patient.setEmail("test@example.com");
        appointment.setPatient(patient);
        reminder.setAppointment(appointment);

        when(reminderRepository.findByAppointment_DateTime(eq(tomorrow)))
                .thenReturn(Collections.singletonList(reminder));

        reminderService.sendRemindersForTomorrowAppointments();

        verify(emailService).sendEmail(
                "test@example.com", "Przypomnienie o jutrzejszej wizycie", "Pamiętaj o jutrzejszej wizycie"
        );
    }

}
