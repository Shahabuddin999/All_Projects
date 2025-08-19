package com.webflux.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.webflux.example.controller.EmployeeController;
import com.webflux.example.dto.EmployeeDto;
import com.webflux.example.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    void testUpdateEmployee_success() {
        Integer id = 1;
        String auth = "Bearer token";
        String filter = "IT";

        EmployeeDto requestDto = new EmployeeDto(1, "John", "IT");
        EmployeeDto responseDto = new EmployeeDto(1, "John Updated", "IT");
        // ✅ Service ko stub kiya with ResponseEntity
        when(employeeService.updateEmployee(id, auth, filter, requestDto))
                .thenReturn(ResponseEntity.accepted().body(responseDto));

        ResponseEntity<EmployeeDto> response =
                employeeController.updateEmployee(id, filter, auth, requestDto);

        assertEquals(202, response.getStatusCodeValue()); // ✅ 202 expected
        assertEquals("John Updated", response.getBody().getName());
    }
}

