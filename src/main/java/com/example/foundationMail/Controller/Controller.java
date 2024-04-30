package com.example.foundationMail.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foundationMail.Model.EmailRequest;
import com.example.foundationMail.Model.Foundation;
import com.example.foundationMail.Model.Nonprofit;
import com.example.foundationMail.Service.EmailService;

@RestController
@RequestMapping("/emails")
public class Controller {

	@GetMapping("/hello")
	public String hello() {
		return "Hello from email controller!";
	}

	@Autowired
	private EmailService emailService;

	@PostMapping(value = "/sendEmail", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		try {

			emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText(), emailRequest.getNgos());
			return ResponseEntity.ok("Email sent successfully");
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/nonprofits")
	public ResponseEntity<Nonprofit> saveNonprofit(@RequestBody Nonprofit nonprofit) {
		Nonprofit savedNonprofit = emailService.saveNonprofit(nonprofit);
		return new ResponseEntity<>(savedNonprofit, HttpStatus.CREATED);
	}

	@PostMapping("/foundations")
	public ResponseEntity<Foundation> saveFoundation(@RequestBody Foundation foundation) {
		Foundation savedFoundation = emailService.saveFoundation(foundation);
		return new ResponseEntity<>(savedFoundation, HttpStatus.CREATED);
	}

}
