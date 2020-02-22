package com.kapil.FlightReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityServiceI {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticateManager;

	@Override
	public boolean login(String username, String password) {
		UserDetails userDetail = userDetailsService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetail, password,
				userDetail.getAuthorities());
		authenticateManager.authenticate(token);
		boolean result = token.isAuthenticated();
		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		return result;
	}

}
