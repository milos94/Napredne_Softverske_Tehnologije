package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Class;

public interface ClassService {

	List<Class> findAll();
	
	Class findById(Long id);
	
	Class findByName(String name);
	
	Class save(Class newClass);
	
	void delete(Class clas);

}