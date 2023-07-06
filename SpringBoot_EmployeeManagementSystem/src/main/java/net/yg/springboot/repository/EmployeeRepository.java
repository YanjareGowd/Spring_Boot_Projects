package net.yg.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.yg.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
