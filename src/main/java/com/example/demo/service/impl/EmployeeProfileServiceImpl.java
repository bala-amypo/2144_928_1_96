package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    // DTO → Entity
    private EmployeeProfile toEntity(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setId(dto.getId());
        e.setEmployeeId(dto.getEmployeeId());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setTeamName(dto.getTeamName());
        e.setRole(dto.getRole());
        e.setActive(dto.isActive());
        return e;
    }

    // Entity → DTO
    private EmployeeProfileDto toDto(EmployeeProfile e) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(e.getId());
        dto.setEmployeeId(e.getEmployeeId());
        dto.setFullName(e.getFullName());
        dto.setEmail(e.getEmail());
        dto.setTeamName(e.getTeamName());
        dto.setRole(e.getRole());
        dto.setActive(e.isActive());
        return dto;
    }

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return toDto(e);
    }
}
