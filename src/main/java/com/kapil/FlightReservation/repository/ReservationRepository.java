package com.kapil.FlightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapil.FlightReservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
