package com.dizify.music.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dizify.music.repository.UserRepository;
import com.dizify.music.entity.User;
import com.dizify.music.entity.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByMail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		System.out.println("det user "+user.get().getpassword()+" "+user.get().getFname());
		return new UserDetailsImpl(user.get().getId(), user.get().getEmail(), user.get().getpassword(), user.get().getFname(), user.get().getLname(), user.get().getAvatar(), user.get().getRole());
	}

	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByMail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getpassword(),new ArrayList<>());
	}
	
	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFname(user.getFname());
		newUser.setLname(user.getLname());
		newUser.setRole(user.getRole());
		newUser.setAvatar(user.getAvatar());
		newUser.setpassword(bcryptEncoder.encode(user.getpassword()));
		return userRepository.save(newUser);
	}

}