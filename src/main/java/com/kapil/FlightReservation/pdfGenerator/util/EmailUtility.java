package com.kapil.FlightReservation.pdfGenerator.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtility {

	@Value("${com.bharath.FightReservation.itinerary.email.body}")
	private String EMAIL_BODY;
	@Value("${com.bharath.FightReservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;
	@Autowired
	private JavaMailSender sender;

	public void sendItinerary(String toAddress, String filePath) {

		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.addAttachment("Itinerary", new File(filePath));
			sender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			e.printStackTrace();
		}

	}
}
