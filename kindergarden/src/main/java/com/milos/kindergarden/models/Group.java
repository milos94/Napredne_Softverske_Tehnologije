package com.milos.kindergarden.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "_group")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long ID;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;
	
	private double price;
	
	private String comment;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_group", joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
									inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
	List<Employee> teachers;
	
	public Group() {
		
	}

	public Group(long iD, String name, Classroom classroom, double price, String comment, List<Employee> teachers) {
		super();
		ID = iD;
		this.name = name;
		this.classroom = classroom;
		this.price = price;
		this.comment = comment;
		this.teachers = teachers;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
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

	public List<Employee> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Employee> teachers) {
		this.teachers = teachers;
	}
	
}
