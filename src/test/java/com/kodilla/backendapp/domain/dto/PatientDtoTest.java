package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientDtoTest {

    @Test
    public void testGettersAndSetters() {
        PatientDto patient = new PatientDto();
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "123456789";

        patient.setId(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPhone(phone);

        assertEquals(id, patient.getId());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(lastName, patient.getLastName());
        assertEquals(email, patient.getEmail());
        assertEquals(phone, patient.getPhone());
    }

    @Test
    public void testNoArgsConstructor() {
        PatientDto patient = new PatientDto();

        assertNull(patient.getId());
        assertNull(patient.getFirstName());
        assertNull(patient.getLastName());
        assertNull(patient.getEmail());
        assertNull(patient.getPhone());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "123456789";

        PatientDto patient = new PatientDto(id, firstName, lastName, email, phone);

        assertEquals(id, patient.getId());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(lastName, patient.getLastName());
        assertEquals(email, patient.getEmail());
        assertEquals(phone, patient.getPhone());
    }

    @Test
    public void testBuilder() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "123456789";

        PatientDto patient = PatientDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .build();

        assertEquals(id, patient.getId());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(lastName, patient.getLastName());
        assertEquals(email, patient.getEmail());
        assertEquals(phone, patient.getPhone());
    }
}
