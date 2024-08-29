package com.kodilla.backendapp.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorDtoTest {

    @Test
    public void testGettersAndSetters() {
        DoctorDto doctorDto = new DoctorDto();

        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String specialization = "Cardiology";

        doctorDto.setId(id);
        doctorDto.setFirstName(firstName);
        doctorDto.setLastName(lastName);
        doctorDto.setSpecialization(specialization);

        assertEquals(id, doctorDto.getId());
        assertEquals(firstName, doctorDto.getFirstName());
        assertEquals(lastName, doctorDto.getLastName());
        assertEquals(specialization, doctorDto.getSpecialization());

        assertNotEquals(2L, doctorDto.getId());
        assertNotEquals("Jane", doctorDto.getFirstName());
        assertNotEquals("Smith", doctorDto.getLastName());
        assertNotEquals("Neurology", doctorDto.getSpecialization());
    }

    @Test
    public void testNoArgsConstructor() {
        DoctorDto doctorDto = new DoctorDto();

        assertNull(doctorDto.getId());
        assertNull(doctorDto.getFirstName());
        assertNull(doctorDto.getLastName());
        assertNull(doctorDto.getSpecialization());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String specialization = "Cardiology";

        DoctorDto doctorDto = new DoctorDto(id, firstName, lastName, specialization);

        assertEquals(id, doctorDto.getId());
        assertEquals(firstName, doctorDto.getFirstName());
        assertEquals(lastName, doctorDto.getLastName());
        assertEquals(specialization, doctorDto.getSpecialization());
    }

    @Test
    public void testBuilder() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String specialization = "Cardiology";

        DoctorDto doctorDto = DoctorDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .specialization(specialization)
                .build();

        assertEquals(id, doctorDto.getId());
        assertEquals(firstName, doctorDto.getFirstName());
        assertEquals(lastName, doctorDto.getLastName());
        assertEquals(specialization, doctorDto.getSpecialization());
    }
}
