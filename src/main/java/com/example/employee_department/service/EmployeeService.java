package com.example.employee_department.service;

import com.example.employee_department.dto.EmployeeDTO;
import com.example.employee_department.entity.Employee;
import com.example.employee_department.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    public List<EmployeeDTO> getAllEmployees() {
        return empRepo.findAll().stream().map(emp -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(emp.getId());
            dto.setName(emp.getName());
            dto.setEmail(emp.getEmail());
            dto.setPosition(emp.getPosition());
            dto.setSalary(emp.getSalary());
            return dto;
        }).collect(Collectors.toList());
    }
}
