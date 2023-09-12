package com.project.crs.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.crs.entity.JwtRequest;
import com.project.crs.entity.JwtResponse;
import com.project.crs.entity.User;
import com.project.crs.repository.UserRepo;
import com.project.crs.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	//@Lazy
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private AuthenticationManager authenticationmanager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);
		
		final UserDetails userDetails = loadUserByUsername(userName);
		
		String newGeneratedToken = jwtutil.generateToken(userDetails);
		User user= userRepo.findById(userName).get();
		
		return new JwtResponse(user, newGeneratedToken);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findById(username).get();
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), 
					user.getUserpassword(),
					getAuthorities(user));
		} 
		else 
		{
			throw new UsernameNotFoundException("username is not valid");
		}
	}

	private Set<SimpleGrantedAuthority> getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {

		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("user is disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials from user");
		}

	}

}
