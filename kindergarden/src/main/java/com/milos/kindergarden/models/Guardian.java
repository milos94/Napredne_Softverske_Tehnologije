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

@Entity
public class Guardian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(11)")
	private Long ID;
	
	private String firstName;
	
	private String lastName;
	
	private String UMCN;
		
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "kid_guardian", joinColumns = @JoinColumn(name = "guardian_id", referencedColumnName = "id"),
										inverseJoinColumns = @JoinColumn(name = "kid_id", referencedColumnName = "id"))
	List<Kid> kids;
	
	private String comment;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account account;
	
	public Guardian() {
		
	}
	
	public Guardian(long iD, String firstName, String lastName, String uMCN, Account account, String password,
			List<Kid> kids, String comment) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		UMCN = uMCN;
		this.account = account;
		this.password = password;
		this.kids = kids;
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
	
	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
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

}
