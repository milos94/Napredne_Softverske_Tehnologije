package com.milos.kindergarden.models;

import java.time.LocalDate;
import java.util.List;

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

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long ID;
	
	private String firstName;
	
	private String lastName;
	
	@Column(columnDefinition = "DATE")
	private LocalDate dateOfEmployment;
	
	private double pay;
	
	private String type;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_group", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
									inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	List<Group> groups;
	
	private String comment;

	public Employee(long iD, String firstName, String lastName, LocalDate dateOfEmployment, double pay, String type,
			String password, List<Group> groups, String comment) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfEmployment = dateOfEmployment;
		this.pay = pay;
		this.type = type;
		this.password = password;
		this.groups = groups;
		this.comment = comment;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
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
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
