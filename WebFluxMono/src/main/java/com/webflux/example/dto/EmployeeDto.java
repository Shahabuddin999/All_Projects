package com.webflux.example.dto;

public class EmployeeDto {
    private Integer id;
    private String name;
    private String department;

    public EmployeeDto() {}

    public EmployeeDto(Integer id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
