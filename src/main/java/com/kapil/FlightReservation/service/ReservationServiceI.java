package com.kapil.FlightReservation.service;

import com.kapil.FlightReservation.dto.ReservationRequest;
import com.kapil.FlightReservation.entities.Reservation;

public interface ReservationServiceI {

	public Reservation bookFlight(ReservationRequest request);
}
