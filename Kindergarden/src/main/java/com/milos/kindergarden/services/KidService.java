package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Kid;

public interface KidService {

	List<Kid> findAll();
	
	Kid findById(Long id);
}