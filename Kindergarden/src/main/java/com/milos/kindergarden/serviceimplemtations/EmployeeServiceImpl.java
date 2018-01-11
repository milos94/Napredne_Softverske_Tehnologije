package com.milos.kindergarden.serviceimplemtations;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Employee;
import com.milos.kindergarden.repositories.EmployeeCrudRepository;
import com.milos.kindergarden.services.EmployeeService;

@Service
@Transactional
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
				.filter(empl -> empl.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Employee save(Employee newEmployee) {
		Employee emp = employees.stream()
									.filter(em -> em.getId().equals(newEmployee.getId()))
									.findFirst().orElse(null);
		if(emp == null) {
			employees.add(newEmployee);
		}
		else {
			Collections.replaceAll(employees, emp, newEmployee);
		}
		return repository.save(newEmployee);
	}
	
	@Override
	public void delete(Employee employee) {
		employees.remove(employee);
		repository.delete(employee);
	}
	
	@Override
	public void refresh() {
		this.load();
	}

	@Override
	public List<Employee> findByType(String type) {
		employees = repository.findByType(type);
		return employees;
	}
	
}
