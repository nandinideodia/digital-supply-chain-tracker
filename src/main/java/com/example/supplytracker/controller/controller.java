package com.example.supplytracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
	
	
	@GetMapping("/welcome")
	public String hello() {
		return "Welcome";
	}

}
