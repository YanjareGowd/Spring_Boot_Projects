package net.yg.springboot.service;

import java.util.List;

import net.yg.springboot.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long Id);
    Employee updateEmployee(Employee employee,long Id);
    void deleteEmployee(long Id);
}
