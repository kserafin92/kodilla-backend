package com.kodilla.backendapp.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private Long id;
    private LocalDateTime dateTime;
    private Long doctorId;
    private Long patientId;
    private Double price;

}
