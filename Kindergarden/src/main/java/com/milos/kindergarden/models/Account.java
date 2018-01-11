package com.milos.kindergarden.models;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long id;
	
	private double balance;
	
	private String comment;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Payment> payments;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Guardian> guardians;
	
	private LocalDateTime lastPayment;
	
	public Account() {
		
	}

	public Account(Long id, double balance, String comment, Set<Payment> payments, Set<Guardian> guardians,
			LocalDateTime lastPayment) {
		super();
		this.id = id;
		this.balance = balance;
		this.comment = comment;
		this.payments = payments;
		this.guardians = guardians;
		this.lastPayment = lastPayment;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Set<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(Set<Guardian> guardians) {
		this.guardians = guardians;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getLastPayment() {
		return lastPayment == null ? LocalDateTime.MIN : lastPayment;
	}

	public void setLastPayment(LocalDateTime lastPayment) {
		this.lastPayment = lastPayment;
	}

}
