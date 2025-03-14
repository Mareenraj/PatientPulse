package com.mareen.patientservice.service;

import com.mareen.patientservice.dto.PatientRequestDto;
import com.mareen.patientservice.dto.PatientResponseDto;
import com.mareen.patientservice.exception.EmailAlreadyExistsException;
import com.mareen.patientservice.mapper.PatientMapper;
import com.mareen.patientservice.model.Patient;
import com.mareen.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientResponseDto> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::entityToDto)
                .toList();
    }

    public PatientResponseDto create(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.getEmail());
        }
        Patient newPatient = patientRepository.save(patientMapper.dtoToEntity(patientRequestDto));
        return patientMapper.entityToDto(newPatient);
    }
}
