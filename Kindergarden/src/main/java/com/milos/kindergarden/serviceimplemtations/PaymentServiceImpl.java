package com.milos.kindergarden.serviceimplemtations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Payment;
import com.milos.kindergarden.repositories.PaymentCrudRepository;
import com.milos.kindergarden.services.PaymenyService;

@Service
public class PaymentServiceImpl implements PaymenyService {
	
	private PaymentCrudRepository repository;
	private List<Payment> payments;
	private int hashCode;
	
	private void load() {
		payments = repository.findAll();
		hashCode = payments.hashCode();
	}
	
	public PaymentServiceImpl() {

	}

	public PaymentServiceImpl(PaymentCrudRepository repositroy, List<Payment> payments) {
		super();
		this.repository = repositroy;
		this.payments = payments;
	}

	public PaymentCrudRepository getRepositroy() {
		return repository;
	}

	public void setRepositroy(PaymentCrudRepository repositroy) {
		this.repository = repositroy;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	/* (non-Javadoc)
	 * @see com.milos.kindergarden.serviceimplemtations.PaymenyService#findAll()
	 */
	@Override
	public List<Payment> findAll(){
		if(payments == null) {
			this.load();
		}
		return payments;
	}

	@Override
	public Payment findById(Long id) {
		if(payments == null) {
			return repository.findById(id);
		}
		return payments.stream()
				.filter(pay -> pay.getId() == id)
				.findFirst().orElse(null);
	}

}
