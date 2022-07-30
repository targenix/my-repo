package com.stacksimplify.restservices.Hello;

public class UserDetails {
	private String firstname;
	private String lastname;
	private String City;
	
	//Fields Constructors
	public UserDetails(String firstname, String lastname, String City) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.City = City;
	}
	
	//Getters and Setters
	public String getFirstname()
	{
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
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
    //To String
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", City=" + City + "]";
	}
	
}
