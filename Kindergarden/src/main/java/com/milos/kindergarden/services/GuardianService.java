package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Guardian;

public interface GuardianService {

	List<Guardian> findAll();

	Guardian findByFirstNameAndLastName(String frstName, String lastName);

	Guardian findById(Long id);
	
	Guardian save(Guardian newGuardian);
	
	void delete(Guardian guardian);
}