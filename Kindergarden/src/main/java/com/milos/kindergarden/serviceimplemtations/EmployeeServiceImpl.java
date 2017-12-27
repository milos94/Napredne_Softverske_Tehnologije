package com.milos.kindergarden.serviceimplemtations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Employee;
import com.milos.kindergarden.repositories.EmployeeCrudRepository;
import com.milos.kindergarden.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeCrudRepository repository;
	private List<Employee> employees;
	private int hashCode;
	
	private void load() {
		employees = repository.findAll();
		hashCode = employees.hashCode();
	}
	
	public EmployeeServiceImpl() {
	}

	@Autowired
	public EmployeeServiceImpl(EmployeeCrudRepository repository) {
		super();
		this.repository = repository;
	}

	public EmployeeCrudRepository getRepository() {
		return repository;
	}

	public void setRepository(EmployeeCrudRepository repository) {
		this.repository = repository;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.EmployeeService#findAll()
	 */
	@Override
	public List<Employee> findAll(){
		if(employees == null) {
			this.load();
		}
		return employees;
	}
	
	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.EmployeeService#findByFirstNameAndLastName(java.lang.String, java.lang.String)
	 */
	@Override
	public Employee findByFirstNameAndLastName(String firstName, String lastName) {
		if(employees == null) {
			return repository.findByFirstNameAndLastName(firstName, lastName);
		}
		return employees.stream()
				.filter(emp -> emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName))
				.findFirst().orElse(null);
	}

	@Override
	public Employee findById(Long id) {
		if(employees == null) {
			return repository.findById(id);
		}
		return employees.stream()
				.filter(empl -> empl.getId() == id)
				.findFirst().orElse(null);
	}
	
}
