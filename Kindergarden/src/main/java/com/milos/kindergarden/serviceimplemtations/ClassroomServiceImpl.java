package com.milos.kindergarden.serviceimplemtations;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Classroom;
import com.milos.kindergarden.repositories.ClassroomCrudRepository;
import com.milos.kindergarden.services.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	
	private ClassroomCrudRepository repository;
	private List<Classroom> classrooms;
	private int hashCode;
	
	private void load() {
		classrooms = repository.findAll();
		hashCode = classrooms.hashCode();
	}
	
	public ClassroomServiceImpl() {

	}
	
	@Autowired
	public ClassroomServiceImpl(ClassroomCrudRepository repository) {
		super();
		this.repository = repository;
	}

	public ClassroomCrudRepository getRepository() {
		return repository;
	}

	public void setRepository(ClassroomCrudRepository repository) {
		this.repository = repository;
	}
	
	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public List<Classroom> findAll(){
		if(classrooms == null) {
			this.load();
		}
		return classrooms;
	}

	@Override
	public Classroom findById(Long id) {
		if(classrooms == null) {
			return repository.findById(id);
		}
		return classrooms.stream()
				.filter(clss -> clss.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Classroom save(Classroom newClassrom) {
		Classroom clsroom = classrooms.stream()
										.filter(cls -> cls.getId().equals(newClassrom.getId()))
										.findFirst().orElse(null);
		if(clsroom == null) {
			classrooms.add(newClassrom);
		}
		else {
			Collections.replaceAll(classrooms, clsroom, newClassrom);
		}
		return repository.save(newClassrom);
	}
	
	@Override
	public void delete(Classroom classroom) {
		classrooms.remove(classroom);
		repository.delete(classroom);
	}
}
