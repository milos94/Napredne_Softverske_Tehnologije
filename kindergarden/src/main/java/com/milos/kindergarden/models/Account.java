package com.milos.kindergarden.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@OneToMany(mappedBy = "acc", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Payment> payments;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Guardian> guardians;
	
	public Account() {
		
	}

	public Account(long iD, double balance, String comment, Set<Payment> payments, Set<Guardian> guardians) {
		super();
		id = iD;
		this.balance = balance;
		this.comment = comment;
		this.payments = payments;
		this.guardians = guardians;
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
	
}
