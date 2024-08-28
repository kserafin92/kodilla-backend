package com.kodilla.backendapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kodilla.backendapp.domain.Reminder;
import com.kodilla.backendapp.service.ReminderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReminderController.class)
public class ReminderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReminderService reminderService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testCreateReminder() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Reminder reminder = new Reminder(null, now, null);
        Reminder savedReminder = new Reminder(1L, now, null);

        when(reminderService.save(any(Reminder.class))).thenReturn(savedReminder);

        // Format LocalDateTime to match the precision used in the response
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");

        mockMvc.perform(post("/appointments/reminders").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reminder))).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(savedReminder.getId())).andExpect(jsonPath("$.reminderTime").value(now.format(formatter))).andExpect(jsonPath("$.appointment").doesNotExist());
    }

}
