package net.yg.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import net.yg.springboot.execption.ResourceNotFoundException;
import net.yg.springboot.model.Employee;
import net.yg.springboot.repository.EmployeeRepository;
import net.yg.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long Id) {
		Optional<Employee> employee = employeeRepository.findById(Id);
		if(employee.isPresent())
		{
			return employee.get();
		}
		else
		{
			throw new ResourceNotFoundException("Employee", "Id",Id);
		}
		//return employeeRepository.findById(id).orElseThrow(() -> 
		// new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long Id) {
		//we need to check whether employee with given id is exit in DB or not
		//Employee existingEmployee = employeeRepository.findById(Id);
//		if(existingEmployee.isPresent())
//		{
//			throw new ResourceNotFoundException("Employee", "Id", Id);
//		}
//		else
//		{
//			existingEmployee.setFirstName(employee.getFirstName());
//			existingEmployee.setLastName(employee.getLastName());
//			existingEmployee.setEmail(employee.getEmail());
//		}
		Employee existingEmployee = employeeRepository.findById(Id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", Id)); 
		
		//Update the existing Employee by the Employee got from client
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long Id) 
	{
		
		//check check whether given id is exit in DB or not
		employeeRepository.findById(Id).orElseThrow(
				()-> new ResourceNotFoundException("Employee", "Id", Id));
		employeeRepository.deleteById(Id);
		
	}
	
	

	

	
	

	 

}
 