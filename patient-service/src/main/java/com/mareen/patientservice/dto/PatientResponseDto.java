package com.mareen.patientservice.dto;

import lombok.*;

@Getter
@Setter
public class PatientResponseDto {
    private String id;

    private String name;

    private String email;

    private String address;

    private String dateOfBirth;
}
