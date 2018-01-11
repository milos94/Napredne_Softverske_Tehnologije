package com.milos.kindergarden.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfEmployment;
	
	private double pay;
	
	private String type;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "teacher_group", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
									inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	Set<Class> classes;
	
	private String comment;
	
	public Employee() {
		classes = new HashSet<Class>();
	}
	
	public Employee(Long id, String firstName, String lastName, LocalDate dateOfEmployment, double pay, String type,
			String password, Set<Class> classes, String comment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfEmployment = dateOfEmployment;
		this.pay = pay;
		this.type = type;
		this.password = password;
		this.classes = classes;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(LocalDate dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
