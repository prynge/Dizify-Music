package com.dizify.music.entity.payload;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
//	private final String jwttoken;
//
//	public JwtResponse(String jwttoken) {
//		this.jwttoken = jwttoken;
//	}
//
//	public String getToken() {
//		return this.jwttoken;
//	}
	

	private String token;
	private String type = "Bearer";
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Integer id, String fname, String lname, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.lname = lname;
		this.fname = fname;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public List<String> getRoles() {
		return roles;
	}
}