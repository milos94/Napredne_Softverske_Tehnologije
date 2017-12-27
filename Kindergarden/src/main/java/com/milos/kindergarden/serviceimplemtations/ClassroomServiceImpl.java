package com.milos.kindergarden.serviceimplemtations;

import java.util.List;

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
	
	public ClassroomServiceImpl(ClassroomCrudRepository repository, List<Classroom> classrooms) {
		super();
		this.repository = repository;
		this.classrooms = classrooms;
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
				.filter(clss -> clss.getId() == id)
				.findFirst().orElse(null);
	}

}
