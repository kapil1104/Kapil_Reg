package com.kapil.FlightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapil.FlightReservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
