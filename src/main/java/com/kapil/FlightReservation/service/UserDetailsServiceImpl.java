package com.kapil.FlightReservation.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kapil.FlightReservation.entities.User;
import com.kapil.FlightReservation.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found for the email" + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getRoles());

	}

}
