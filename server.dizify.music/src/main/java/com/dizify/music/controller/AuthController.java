package com.dizify.music.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.security.JwtUtils;
import com.dizify.music.entity.payload.JwtRequest;
import com.dizify.music.entity.payload.JwtResponse;
import com.dizify.music.entity.payload.MessageResponse;
import com.dizify.music.repository.RoleRepository;
import com.dizify.music.repository.UserRepository;
import com.dizify.music.entity.ERole;
import com.dizify.music.entity.Role;
import com.dizify.music.entity.User;
import com.dizify.music.entity.UserDTO;
import com.dizify.music.service.JwtUserDetailsService;
import com.dizify.music.service.UserDetailsImpl;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JwtUtils jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getFname(), 
				 userDetails.getLname(), 
				 userDetails.getEmail(), 
				 roles));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		if (userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		// Create new user's account
		User newUser = new User(user.getFname(), user.getLname(), user.getEmail(),
							 bcryptEncoder.encode(user.getPassword()));

		String strRoles = user.getRole();
		Role roles;

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles = userRole;
		} else {
			switch (strRoles) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles = adminRole;

				break;
			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles = userRole;
			}
		}

		newUser.setRole(roles);
		
		
		return ResponseEntity.ok(userDetailsService.save(newUser));
	}

}