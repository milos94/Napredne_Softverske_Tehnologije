package com.milos.kindergarden.models;

import java.util.Set;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Guardian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String UMCN;
		
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "kid_guardian", joinColumns = @JoinColumn(name = "guardian_id", referencedColumnName = "id"),
										inverseJoinColumns = @JoinColumn(name = "kid_id", referencedColumnName = "id"))
	Set<Kid> kids;
	
	private String comment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;
	
	public Guardian() {
		
	}
	
	public Guardian(long iD, String firstName, String lastName, String uMCN, Account account, String password,
			Set<Kid> kids, String comment) {
		super();
		id = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		UMCN = uMCN;
		this.account = account;
		this.password = password;
		this.kids = kids;
		this.comment = comment;
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
	
	public String getUMCN() {
		return UMCN;
	}
	
	public void setUMCN(String uMCN) {
		UMCN = uMCN;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
