package com.kapil.FlightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapil.FlightReservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Long>{

}
