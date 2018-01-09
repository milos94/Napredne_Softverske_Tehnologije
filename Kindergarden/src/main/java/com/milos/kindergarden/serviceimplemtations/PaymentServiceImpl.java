package com.milos.kindergarden.serviceimplemtations;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.kindergarden.models.Payment;
import com.milos.kindergarden.repositories.PaymentCrudRepository;
import com.milos.kindergarden.services.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	private PaymentCrudRepository repository;
	private List<Payment> payments;
	private int hashCode;
	
	private void load() {
		payments = repository.findAll();
		hashCode = payments.hashCode();
	}
	
	public PaymentServiceImpl() {

	}

	@Autowired
	public PaymentServiceImpl(PaymentCrudRepository repositroy) {
		super();
		this.repository = repositroy;
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
				.filter(pay -> pay.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public Payment save(Payment newPayment) {
		Payment pay = payments.stream()
								.filter(p-> p.getId().equals(newPayment.getId()))
								.findFirst().orElse(null);
		if(pay == null) {
			payments.add(newPayment);
		}
		else {
			Collections.replaceAll(payments, pay, newPayment);
		}
							
		return repository.save(newPayment);
	}
	
	@Override
	public void delete(Payment payment) {
		payments.remove(payment);
		repository.delete(payment);
	}
	
	@Override
	public void refresh() {
		this.load();
	}
}
