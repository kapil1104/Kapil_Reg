package com.kapil.FlightReservation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapil.FlightReservation.entities.Flight;
import com.kapil.FlightReservation.repository.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;

	@RequestMapping("/findFlighs")
	public String findFlighs(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
			ModelMap modelMap) {
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		return "login/displayFlights";
	}

	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "login/addFlight";
	}

}
