package com.familyapp.application;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FamilyAppApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_KEY", dotenv.get("JWT_KEY"));
		System.setProperty("JWT_EXP", dotenv.get("JWT_EXP"));
		System.setProperty("CLIENT_ID", dotenv.get("CLIENT_ID"));
		System.setProperty("CLIENT_SECRET", dotenv.get("CLIENT_SECRET"));
		System.setProperty("HOST", dotenv.get("HOST"));
		System.setProperty("PORT", dotenv.get("PORT"));
		System.setProperty("USERNAME", dotenv.get("USERNAME"));
		System.setProperty("PASSWORD", dotenv.get("PASSWORD"));
		System.setProperty("PROTOCOL", dotenv.get("PROTOCOL"));
		SpringApplication.run(FamilyAppApplication.class, args);

	}

}
