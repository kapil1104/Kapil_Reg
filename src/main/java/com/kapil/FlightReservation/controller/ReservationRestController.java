package com.kapil.FlightReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapil.FlightReservation.dto.ReservationUpdateRequest;
import com.kapil.FlightReservation.entities.Reservation;
import com.kapil.FlightReservation.repository.ReservationRepository;

@RestController
public class ReservationRestController {

	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		return reservationRepository.findOne(id);
	}

	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Reservation reservation = reservationRepository.findOne(request.getId());
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());

		return reservationRepository.save(reservation);
	}

}
