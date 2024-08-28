package com.kodilla.backendapp.controller;

import com.kodilla.backendapp.domain.Reminder;
import com.kodilla.backendapp.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments/reminders")
public class ReminderController {
    private final ReminderService reminderService;
    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }
    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderService.save(reminder);
    }
}