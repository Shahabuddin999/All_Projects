package com.webflux.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webflux.example.dto.EmployeeDto;


@Service
public class EmployeeService {

    public ResponseEntity<EmployeeDto> updateEmployee(Integer id, String auth, String filter, EmployeeDto dto) {
        // Normally authentication & DB update logic goes here
        if (auth == null || !auth.startsWith("Bearer")) {
            return ResponseEntity.status(401).build();
        }

        // Mock updated employee
        dto.setId(id);
        dto.setDepartment(filter); // just for demo
        dto.setName(dto.getName() + " Updated");

        return ResponseEntity.accepted().body(dto);
    }
}
