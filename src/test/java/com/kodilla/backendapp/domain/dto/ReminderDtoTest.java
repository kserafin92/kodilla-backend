package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReminderDtoTest {

    @Test
    public void testGettersAndSetters() {
        ReminderDto reminder = new ReminderDto();
        Long id = 1L;
        LocalDateTime reminderTime = LocalDateTime.now();
        Long patientId = 2L;
        Long appointmentId = 3L;

        reminder.setId(id);
        reminder.setReminderTime(reminderTime);
        reminder.setPatientId(patientId);
        reminder.setAppointmentId(appointmentId);

        assertEquals(id, reminder.getId());
        assertEquals(reminderTime, reminder.getReminderTime());
        assertEquals(patientId, reminder.getPatientId());
        assertEquals(appointmentId, reminder.getAppointmentId());
    }

    @Test
    public void testNoArgsConstructor() {
        ReminderDto reminder = new ReminderDto();

        assertNull(reminder.getId());
        assertNull(reminder.getReminderTime());
        assertNull(reminder.getPatientId());
        assertNull(reminder.getAppointmentId());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        LocalDateTime reminderTime = LocalDateTime.now();
        Long patientId = 2L;
        Long appointmentId = 3L;

        ReminderDto reminder = new ReminderDto(id, reminderTime, patientId, appointmentId);

        assertEquals(id, reminder.getId());
        assertEquals(reminderTime, reminder.getReminderTime());
        assertEquals(patientId, reminder.getPatientId());
        assertEquals(appointmentId, reminder.getAppointmentId());
    }

    @Test
    public void testBuilder() {
        Long id = 1L;
        LocalDateTime reminderTime = LocalDateTime.now();
        Long patientId = 2L;
        Long appointmentId = 3L;

        ReminderDto reminder = ReminderDto.builder()
                .id(id)
                .reminderTime(reminderTime)
                .patientId(patientId)
                .appointmentId(appointmentId)
                .build();

        assertEquals(id, reminder.getId());
        assertEquals(reminderTime, reminder.getReminderTime());
        assertEquals(patientId, reminder.getPatientId());
        assertEquals(appointmentId, reminder.getAppointmentId());
    }
}
