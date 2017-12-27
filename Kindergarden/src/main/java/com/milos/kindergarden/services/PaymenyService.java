package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Payment;

public interface PaymenyService {

	List<Payment> findAll();
	
	Payment findById(Long id);

}