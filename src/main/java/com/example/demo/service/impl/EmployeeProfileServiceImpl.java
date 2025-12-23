package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    private EmployeeProfileDto map(EmployeeProfile e) {
        EmployeeProfileDto d = new EmployeeProfileDto();
        d.id = e.getId();
        d.name = e.getName();
        d.role = e.getRole();
        d.team = e.getTeam();
        return d;
    }

    @Override
    public EmployeeProfileDto save(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setName(dto.name);
        e.setRole(dto.role);
        e.setTeam(dto.team);
        return map(repo.save(e));
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        return map(repo.findById(id).orElseThrow());
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile e = repo.findById(id).orElseThrow();
        e.setName(dto.name);
        e.setRole(dto.role);
        e.setTeam(dto.team);
        return map(repo.save(e));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
