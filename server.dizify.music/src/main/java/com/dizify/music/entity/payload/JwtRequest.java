package com.dizify.music.entity.payload;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	@Autowired
	
	private String username;
	private String email;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public JwtRequest(String email, String password, String username) {
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}