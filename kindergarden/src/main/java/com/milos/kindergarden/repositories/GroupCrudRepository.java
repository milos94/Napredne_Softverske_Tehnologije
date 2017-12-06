package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Group;

public interface GroupCrudRepository extends CrudRepository<Group, Long>{
	
	List<Group> findAll();

}
