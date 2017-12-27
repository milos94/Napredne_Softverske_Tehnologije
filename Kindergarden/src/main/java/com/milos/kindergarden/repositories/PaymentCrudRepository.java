package com.milos.kindergarden.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.milos.kindergarden.models.Payment;

public interface PaymentCrudRepository extends CrudRepository<Payment, Long>{
	
	List<Payment> findAll();
	Payment findById(Long id);
}
