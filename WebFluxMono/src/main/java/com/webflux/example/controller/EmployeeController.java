package com.webflux.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webflux.example.dto.EmployeeDto;
import com.webflux.example.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    
	  private final EmployeeService employeeService;

	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Integer id,
            @RequestParam(required = false, defaultValue = "General") String filter,
            @RequestHeader("Authorization") String authorization,
            @RequestBody EmployeeDto employeeDto) {
    	
        return employeeService.updateEmployee(id, authorization, filter, employeeDto);
    }
}
