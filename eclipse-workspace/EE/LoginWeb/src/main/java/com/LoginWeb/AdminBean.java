package com.LoginWeb;

/**
 * AdminBean - Data Transfer Object for Admin
 * Holds admin information
 */
public class AdminBean {
	private int id;
	private String email;
	private String password;
	private String name;
	
	// Constructors
	public AdminBean() {
		super();
	}
	
	public AdminBean(int id, String email, String password, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public AdminBean(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "AdminBean [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
}
