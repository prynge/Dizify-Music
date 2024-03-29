package com.dizify.music.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dizify.music.entity.Role;
import com.dizify.music.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String lname;
	
	private String fname;

	private String email;

	private String avatar;
	
	private String role;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;


	public UserDetailsImpl(Integer id, String fname, String lname, String email, String password, String avatar, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.avatar = avatar;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getName().name()));
		
		return new UserDetailsImpl(
				user.getId(), 
				user.getFname(), 
				user.getLname(), 
				user.getEmail(),
				user.getPassword(), 
				user.getAvatar(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

}