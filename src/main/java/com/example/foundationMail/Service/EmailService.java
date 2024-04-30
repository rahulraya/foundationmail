package com.example.foundationMail.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.foundationMail.Model.Foundation;
import com.example.foundationMail.Model.Nonprofit;
import com.example.foundationMail.Model.Repository.FoundationRepository;
import com.example.foundationMail.Model.Repository.NonprofitRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	
	  @Autowired 
	  private NonprofitRepository nonprofitRepository;
	  
	  @Autowired 
	  private FoundationRepository foundationRepository;
	  
	 
	@Value("$(spring.mail.username)")
	private String from;

	public void sendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}

	
	  public Nonprofit saveNonprofit(Nonprofit nonprofit) { 
		  return  nonprofitRepository.save(nonprofit); 
	   }
	  
	  public Foundation saveFoundation(Foundation foundation) { 
		  return foundationRepository.save(foundation); 
	  }
	 
}
