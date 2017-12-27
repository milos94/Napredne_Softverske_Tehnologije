package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.milos.kindergarden.models.Guardian;

@Repository
public interface GuardianCrudRepository extends CrudRepository<Guardian, Long>{
	
	public List<Guardian> findAll();
	Guardian findByFirstNameAndLastName(String firstName, String lastName);
	Guardian findById(Long id);

}
