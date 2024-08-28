package com.kodilla.backendapp.controller;

import com.kodilla.backendapp.domain.Appointment;
import com.kodilla.backendapp.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDateTime(LocalDateTime.of(2024, 8, 23, 10, 0));
        when(appointmentService.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentService.save(any(Appointment.class))).thenReturn(appointment);
    }

    @Test
    void testGetAllAppointments() throws Exception {
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateAppointment() throws Exception {
        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dateTime\":\"2024-08-23T10:00:00\",\"doctor\":{\"id\":1},\"patient\":{\"id\":1}}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateAppointment() throws Exception {
        mockMvc.perform(put("/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dateTime\":\"2024-08-23T10:00:00\",\"doctor\":{\"id\":1},\"patient\":{\"id\":1}}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteAppointment() throws Exception {
        mockMvc.perform(delete("/appointments/1"))
                .andExpect(status().isNoContent());
    }
}
