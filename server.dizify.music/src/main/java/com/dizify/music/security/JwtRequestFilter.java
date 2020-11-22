package com.dizify.music.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dizify.music.service.JwtAdminDetailsService;
import com.dizify.music.service.JwtUserDetailsService;
import com.dizify.music.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtAdminDetailsService jwtAdminDetailsService;
	
	@Autowired
	private JwtUtils jwtTokenUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String email = null;
		String jwtToken = null;
		boolean admin = true;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				email = jwtTokenUtil.getUsernameFromToken(jwtToken);
				if (email == null) {
					admin = false;
					email = jwtTokenUtil.getEmailFromToken(jwtToken);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails;
			userDetails = this.jwtUserDetailsService.loadUserByEmail(email);
			if (userDetails == null) {
				userDetails = this.jwtAdminDetailsService.loadUserByUsername(email);
			}

			// if token is valid configure Spring Security to manually set
			// authentication
			boolean validToken;
			if(admin == true ) {
				validToken = jwtTokenUtil.validateAdminToken(jwtToken, userDetails);
			} else {
				validToken = jwtTokenUtil.validateToken(jwtToken, userDetails);
			}
				
			
			if (validToken) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}