package com.example.foundationMail.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foundationMail.Model.AuthRequest;
import com.example.foundationMail.Model.EmailRequest;
import com.example.foundationMail.Model.Foundation;
import com.example.foundationMail.Model.Nonprofit;
import com.example.foundationMail.Model.UserInfo;
import com.example.foundationMail.Service.EmailService;
import com.example.foundationMail.Service.JwtService;
import com.example.foundationMail.Service.UserInfoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/emails")
public class Controller {

	@GetMapping("/hello")
	public String hello() {
		return "Hello from email controller!";
	}

	@Autowired
	private EmailService emailService;
	@Autowired
	private UserInfoService service; 
	@Autowired
	private JwtService jwtService; 
	@Autowired
	private AuthenticationManager authenticationManager; 

	@PostMapping("/register") 
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) { 
        String response = service.addUser(userInfo); 
        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
    } 
  
    @PostMapping("/generateToken") 
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token); 
        } else { 
            throw new UsernameNotFoundException("Invalid user request!"); 
        } 
    } 


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
	
	@GetMapping("/ngos")
	public List<String> getNgosMail() {
		return emailService.getNgosMail();
	}

}
