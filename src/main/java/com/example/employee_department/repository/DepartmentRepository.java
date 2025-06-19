package com.example.employee_department.repository;

import com.example.employee_department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    //public interface DepartmentRepository extends JpaRepository<Department, String> {}
    //public interface EmployeeRepository extends JpaRepository<Employee, String> {
    //    List<Employee> findByDepartmentId(String departmentId);
    //}
}
