package com.kodilla.backendapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.backendapp.domain.Doctor;
import com.kodilla.backendapp.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setSpecialization("Cardiology");

        when(doctorService.findById(anyLong())).thenReturn(java.util.Optional.of(doctor));
        when(doctorService.save(any(Doctor.class))).thenReturn(doctor);
        when(doctorService.findAll()).thenReturn(java.util.List.of(doctor));
    }

    @Test
    void testGetAllDoctors() throws Exception {
        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"specialization\":\"Cardiology\"}]"));
    }


    @Test
    void testGetDoctorById() throws Exception {
        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"specialization\":\"Cardiology\"}"));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doctor = Doctor.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .specialization("Cardiology")
                .build();

        when(doctorService.createDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"specialization\":\"Cardiology\"}"));
    }


    @Test
    void testUpdateDoctor() throws Exception {
        Doctor doctor = Doctor.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .specialization("Cardiology")
                .build();

        mockMvc.perform(put("/doctors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"specialization\":\"Cardiology\"}"));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        mockMvc.perform(delete("/doctors/1"))
                .andExpect(status().isNoContent());
    }
}
