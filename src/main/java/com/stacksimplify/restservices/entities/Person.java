package com.stacksimplify.restservices.entities;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


//Entity
@Entity
@Table(name="person")
@JsonIgnoreProperties({"firstname","lastname"})
public class Person extends RepresentationModel<Person>{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message="Personname is Mandatory field. Please provide Personname")
	@Column(name="PERSON_NAME",length=50,nullable=false,unique=true)
	private String username;
	
	@Size(min=2, message="FirstName should have atleast 2 characters")
	@Column(name="FIRST_NAME",length=50,nullable=false)
	private String firstname;
	
	@Column(name="LAST_NAME",length=50,nullable=false)
	private String lastname;
	
	@Column(name="EMAIL",length=50,nullable=false)
	private String email;
	
	@Column(name="ROLE",length=50,nullable=false)
	private String role;
	
	@Column(name="SSN",length=50,nullable=false,unique=true)
	@JsonIgnore
	private String ssn;
	
	@OneToMany(mappedBy="person")
	private List<Order> orders;
	
	
	//No Argument Constructor

	public Person() {
		
		// TODO Auto-generated constructor stub
	}
	
	//Fields Constructor

	public Person(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	//To String-(optional)
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
}
