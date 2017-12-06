package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Kid;

public interface KidCrudRepository extends CrudRepository<Kid, Long>{
	
	List<Kid> findAll();

}
