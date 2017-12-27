package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Class;

public interface ClassCrudRepository extends CrudRepository<Class, Long>{
	
	List<Class> findAll();
	Class findById(Long id);
	Class findByName(String name);

}
