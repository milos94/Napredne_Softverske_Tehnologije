package com.milos.kindergarden.serviceimplemtations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Employee;
import com.milos.kindergarden.models.Guardian;
import com.milos.kindergarden.security.EmployeeUserDetails;
import com.milos.kindergarden.security.GuardianUserDetails;
import com.milos.kindergarden.services.EmployeeService;
import com.milos.kindergarden.services.GuardianService;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

	private GuardianService guardianRepository;
	
	private EmployeeService employeeCrudRepository;
	
	public MyUserDetailsServiceImpl(GuardianService guardianRepository, EmployeeService employeeCrudRepository) {
		super();
		this.guardianRepository = guardianRepository;
		this.employeeCrudRepository = employeeCrudRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		String[] parts = name.split("\\.");
		if(parts.length != 2) {
			throw new UsernameNotFoundException(name);
		}
		Guardian guard = guardianRepository.findByFirstNameAndLastName(parts[0], parts[1]);
		if(guard == null) {
			Employee employee = employeeCrudRepository.findByFirstNameAndLastName(parts[0], parts[1]);
			if(employee == null) {
				throw new UsernameNotFoundException(name);
			}
			return new EmployeeUserDetails(employee);
		}
		return new GuardianUserDetails(guard);
	}
	
	public GuardianService getGuardianRepository() {
		return guardianRepository;
	}

	public void setGuardianRepository(GuardianService guardianRepository) {
		this.guardianRepository = guardianRepository;
	}

	public EmployeeService getEmployeeCrudRepository() {
		return employeeCrudRepository;
	}

	public void setEmployeeCrudRepository(EmployeeService employeeCrudRepository) {
		this.employeeCrudRepository = employeeCrudRepository;
	}
	
	
}
