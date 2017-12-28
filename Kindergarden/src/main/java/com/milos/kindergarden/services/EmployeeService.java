package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findByFirstNameAndLastName(String firstName, String lastName);

	Employee findById(Long id);
	
	Employee save(Employee newEmployee);
	
	void delete(Employee employee);
}