package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Employee;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Long>{
	
	List<Employee> findAll();
	Employee findByFirstNameAndLastName(String firstName,String lastName);
	Employee findById(Long id);
	List<Employee> findByType(String type);
}
