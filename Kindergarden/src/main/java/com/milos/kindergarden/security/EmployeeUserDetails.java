package com.milos.kindergarden.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.milos.kindergarden.models.Employee;

public class EmployeeUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	Employee employee;
	
	Role role;

	public EmployeeUserDetails(Employee employee) {
		super();
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		
		String role = "ROLE_";
		if(employee.getType().equalsIgnoreCase("teacher")) {
			role+="TEACHER";
		}
		else {
			role+="EMPLOYEE";
		}
        list.add(new SimpleGrantedAuthority(role));

        return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return employee.getFirstName()+'.'+employee.getLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Employee getUser() {
		return employee;
	}
	

}
