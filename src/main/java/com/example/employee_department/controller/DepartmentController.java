package com.example.employee_department.controller;

import com.example.employee_department.dto.EmployeeDTO;
import com.example.employee_department.dto.DepartmentDTO;
import com.example.employee_department.service.DepartmentService;
import com.example.employee_department.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok(createdDepartment);
    }


    @GetMapping("/{deptId}/employees")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable String deptId) {
        return departmentService.getEmployeesByDepartment(deptId);
    }

    @PostMapping("/{deptId}/employees")
    public ResponseEntity<EmployeeDTO> addEmployee(@PathVariable String deptId, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(departmentService.addEmployeeToDepartment(deptId, employeeDTO));
    }

    @DeleteMapping("/{deptId}/employees/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String deptId, @PathVariable String empId) {
        departmentService.deleteEmployeeFromDepartment(deptId, empId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{deptId}/report")
    public ResponseEntity<byte[]> getEmployeeReport(@PathVariable String deptId) {
        List<EmployeeDTO> employeeList = departmentService.getEmployeesByDepartment(deptId);
        try {
            byte[] pdfBytes = reportService.exportEmployeeReport(employeeList);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=rep.pdf")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
