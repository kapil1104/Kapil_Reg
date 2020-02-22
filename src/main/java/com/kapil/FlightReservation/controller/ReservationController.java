package com.kapil.FlightReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapil.FlightReservation.dto.ReservationRequest;
import com.kapil.FlightReservation.entities.Flight;
import com.kapil.FlightReservation.entities.Reservation;
import com.kapil.FlightReservation.repository.FlightRepository;
import com.kapil.FlightReservation.service.ReservationServiceI;

@Controller
public class ReservationController {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationServiceI service;

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Flight flight = flightRepository.findOne(flightId);
		modelMap.addAttribute("flight", flight);
		return "login/completeReservation";

	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String compleReservation(ReservationRequest request, ModelMap modelMap) {
		Reservation reservation = service.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation addedd succesfully id is::" + reservation.getId());
		System.out.println("print the message is::");
		return "login/reservationConfirmation";
	}
}