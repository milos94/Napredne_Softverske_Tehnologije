package com.milos.kindergarden.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Class {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long id;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;
	
	private double price;
	
	private String comment;
	
	@ManyToMany( mappedBy = "classes"  ,cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	Set<Employee> teachers;
	
	public Class() {

	}

	public Class(long iD, String name, Classroom classroom, double price, String comment, Set<Employee> teachers) {
		super();
		id = iD;
		this.name = name;
		this.classroom = classroom;
		this.price = price;
		this.comment = comment;
		this.teachers = teachers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<Employee> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Employee> teachers) {
		this.teachers = teachers;
	}
	
}
