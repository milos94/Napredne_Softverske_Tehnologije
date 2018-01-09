package com.milos.kindergarden.services;

import java.util.List;

import com.milos.kindergarden.models.Payment;

public interface PaymentService {

	List<Payment> findAll();
	
	Payment findById(Long id);
	
	Payment save(Payment newPayment);
	
	void delete(Payment payment);

	void refresh();

}