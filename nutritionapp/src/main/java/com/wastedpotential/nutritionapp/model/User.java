package com.wastedpotential.nutritionapp.model;

public class User {

	private Integer id;
	private String name;
	private String address;
	private String password;
	private String email;
	private String contact;
	private int status;
	
	public User() {
	}
	
	public User(Integer id, String name, String address, String password, String email, String contact, int status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}