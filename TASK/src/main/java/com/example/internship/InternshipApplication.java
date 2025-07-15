package com.example.internship;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


public class InternshipApplication {
	@Value("${app.jwt-secret}")
	private  static String jwtSecret;
	//private static String jwtsecret;

	public static void main(String[] args) {

		SpringApplication.run(InternshipApplication.class, args);
		System.out.println("JWT KEY"+ jwtSecret);
	}

}
