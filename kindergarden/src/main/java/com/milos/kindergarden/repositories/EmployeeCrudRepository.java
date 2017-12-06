package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Employee;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Long>{
	
	List<Employee> findAll();
}