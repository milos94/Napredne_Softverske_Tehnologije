package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Classroom;

public interface ClassroomCrudRepository extends CrudRepository<Classroom, Long> {

	List<Classroom> findAll();
}
