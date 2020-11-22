package com.dizify.music.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dizify.music.repository.AdminRepository;
import com.dizify.music.repository.UserRepository;
import com.dizify.music.entity.Admin;
import com.dizify.music.entity.User;
import com.dizify.music.entity.UserDTO;

@Service
public class JwtAdminDetailsService implements UserDetailsService {

	@Autowired
	AdminRepository adminRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return AdminDetailsImpl.build(admin);
	}
}