package com.springrest.springrest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springrest.springrest.exception.ResourceNotFoundException;
import com.springrest.springrest.model.Employee;
import com.springrest.springrest.repository.EmployeeRepository;
import com.springrest.springrest.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if (employee.isPresent()) 
//			return employee.get();
//		else 
//			throw new ResourceNotFoundException("Employee", "Id", id);

		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// check whether particular employee exists in db or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> 
																	new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		// save existing employee to db
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		// check whether particular employee exists in db or not
		employeeRepository.findById(id)
						  .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		
		// delete employee from db
		employeeRepository.deleteById(id);
	
	}

}
