package com.kodilla.backendapp.mapper;

import com.kodilla.backendapp.domain.Reminder;
import com.kodilla.backendapp.domain.dto.AppointmentDto;
import com.kodilla.backendapp.domain.dto.ReminderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderMapper {

    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;

    public ReminderDto toDto(Reminder reminder) {
        return ReminderDto.builder()
                .id(reminder.getId())
                .reminderTime(reminder.getReminderTime())
                .appointmentId(reminder.getAppointment() != null ? reminder.getAppointment().getId() : null)
                .build();
    }

    public Reminder toEntity(ReminderDto reminderDto) {
        return Reminder.builder()
                .id(reminderDto.getId())
                .reminderTime(reminderDto.getReminderTime())
                .appointment(reminderDto.getAppointmentId() != null
                        ? appointmentMapper.toEntity(AppointmentDto.builder().id(reminderDto.getAppointmentId()).build())
                        : null)
                .build();
    }
}
