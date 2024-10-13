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
		//System.setProperty("CLIENT_ID", dotenv.get("CLIENT_ID"));
		//System.setProperty("CLIENT_SECRET", dotenv.get("CLIENT_SECRET"));
		SpringApplication.run(FamilyAppApplication.class, args);

	}

}
