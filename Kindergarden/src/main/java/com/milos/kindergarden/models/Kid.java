package com.milos.kindergarden.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Kid {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Class group;
	
	@ManyToMany( mappedBy = "kids", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	Set<Guardian> guardians;
	
	private String comment;
	
	public Kid(){
		
	}
	
	public Kid(Long id, String firstName, String lastName, LocalDate dateOfBirth, Class group, Set<Guardian> guardians,
			String comment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.group = group;
		this.guardians = guardians;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Class getGroup() {
		return group;
	}

	public void setGroup(Class group) {
		this.group = group;
	}

	public Set<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(Set<Guardian> guardians) {
		this.guardians = guardians;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
