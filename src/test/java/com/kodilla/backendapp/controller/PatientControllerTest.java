package com.kodilla.backendapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.backendapp.domain.Patient;
import com.kodilla.backendapp.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    private Patient existingPatient;
    private Patient updatedPatient;
    private Patient newPatient;

    @BeforeEach
    void setUp() {
        existingPatient = Patient.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("987654321")
                .appointments(Collections.emptyList())
                .reviews(Collections.emptyList())
                .build();

        updatedPatient = Patient.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .phone("123456789")
                .appointments(Collections.emptyList())
                .reviews(Collections.emptyList())
                .build();

        newPatient = Patient.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .phone("123456789")
                .appointments(Collections.emptyList())
                .reviews(Collections.emptyList())
                .build();
    }

    @Test
    void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetPatientById() throws Exception {
        when(patientService.findById(1L)).thenReturn(Optional.of(existingPatient));

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testCreatePatient() throws Exception {
        when(patientService.save(any(Patient.class))).thenReturn(newPatient);

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPatient)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testUpdatePatient() throws Exception {
        when(patientService.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(patientService.save(any(Patient.class))).thenReturn(updatedPatient);

        mockMvc.perform(put("/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPatient)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testDeletePatient() throws Exception {
        when(patientService.findById(1L)).thenReturn(Optional.of(existingPatient));

        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isNoContent());
    }
}
