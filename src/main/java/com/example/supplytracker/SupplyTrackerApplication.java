package com.example.supplytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.supplytracker.entity")
public class SupplyTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyTrackerApplication.class, args);
	}

}
