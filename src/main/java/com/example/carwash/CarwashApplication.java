package com.example.carwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarwashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarwashApplication.class, args);
	}

}
