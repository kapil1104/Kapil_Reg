package com.kapil.FlightReservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kapil.FlightReservation.entities.User;
import com.kapil.FlightReservation.repository.UserRepository;
import com.kapil.FlightReservation.service.SecurityServiceI;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityServiceI securityService;

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		logger.info("showuSER");
		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String saveRegistartion(@ModelAttribute("user") User user) {
		logger.info("saveRegistration");
		// security
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}

	@RequestMapping("/showLogin")
	public String showLogin() {
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {

		boolean loginResponse = securityService.login(email, password);

		if (loginResponse) {
			return "login/findFlights";
		} else {
			modelMap.addAttribute("msg", "user is Invalid, Please try again");
		}
		return "login/login";

	}
	/*
	 * User user = userRepository.findByEmail(email); if
	 * (user.getPassword().equals(password)) { return "login/findFlights"; } else {
	 * modelMap.addAttribute("msg", "user is Invalid, Please try again"); } return
	 * "login/login"; }
	 */}
