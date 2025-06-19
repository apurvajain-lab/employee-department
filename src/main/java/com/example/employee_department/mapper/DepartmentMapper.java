package com.example.employee_department.mapper;

import com.example.employee_department.dto.DepartmentDTO;
import com.example.employee_department.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDto(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());
        // if it has employees, map those too (optional)
        return dto;
    }

    public Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        // map employee list if needed
        return department;
    }
}
