package com.milos.kindergarden.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Kid {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long ID;
	
	private String firstName;
	
	private String lastName;
	
	@Column(columnDefinition = "DATE")
	private LocalDate dateOfBirth;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "kid_guardian", joinColumns = @JoinColumn(name = "kid_id", referencedColumnName = "id"),
										inverseJoinColumns = @JoinColumn(name = "guardian_id", referencedColumnName = "id"))
	List<Guardian> guardians;
	
	private String comment;
	
	public Kid(){
		
	}
	
	public Kid(long iD, String firstName, String lastName, LocalDate dateOfBirth, Group group, 
			List<Guardian> guardians, String comment) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.guardians = guardians;
		this.group = group;
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
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

}
