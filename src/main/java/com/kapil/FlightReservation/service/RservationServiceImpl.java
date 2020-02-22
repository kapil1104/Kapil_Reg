package com.kapil.FlightReservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kapil.FlightReservation.dto.ReservationRequest;
import com.kapil.FlightReservation.entities.Flight;
import com.kapil.FlightReservation.entities.Passenger;
import com.kapil.FlightReservation.entities.Reservation;
import com.kapil.FlightReservation.pdfGenerator.util.EmailUtility;
import com.kapil.FlightReservation.pdfGenerator.util.PdfGenerator;
import com.kapil.FlightReservation.repository.FlightRepository;
import com.kapil.FlightReservation.repository.PassengerRepository;
import com.kapil.FlightReservation.repository.ReservationRepository;

@Service
public class RservationServiceImpl implements ReservationServiceI {

	private static final Logger logger = LoggerFactory.getLogger(RservationServiceImpl.class);

	@Value("${com.kapil.FightReservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	private FlightRepository FlightRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailUtility emailUtil;

	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		logger.info("bookFlight" + request.getCardNumber());
		Long flightId = request.getFlightId();
		Flight flight = FlightRepository.findOne(flightId);

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());

		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();

		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = ITINERARY_DIR + savedReservation.getId() + " .pdf";

		pdfGenerator.generateItinenary(savedReservation, filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
