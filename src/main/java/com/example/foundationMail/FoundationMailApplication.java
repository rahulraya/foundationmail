package com.example.foundationMail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.foundationMail")
//@EnableJpaRepositories(basePackages = "com.example.foundationMail.Model.Repository")
public class FoundationMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoundationMailApplication.class, args);
	}

}
