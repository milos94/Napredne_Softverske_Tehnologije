package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Classroom;

public interface ClassroomService {
	
	List<Classroom> findAll();
	
	Classroom findById(Long id);
	
	Classroom save(Classroom newClassrom);
	
	void delete(Classroom clasroom);

	void refresh();
}
