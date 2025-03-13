package com.mareen.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequestDto {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name characters can't be exceed than 100")
    private String name;

    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotBlank(message = "Address is required!")
    private String address;

    @NotBlank(message = "Date of Birth is required!")
    private String dateOfBirth;

    @NotBlank(message = "Registered date is required!")
    private String registeredDate;
}
