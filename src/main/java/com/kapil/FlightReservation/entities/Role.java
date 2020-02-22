package com.kapil.FlightReservation.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends AbstractEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	@ManyToMany(mappedBy = "roles")
	
	private Set<User> user;

	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {

		return name;
	}

}
