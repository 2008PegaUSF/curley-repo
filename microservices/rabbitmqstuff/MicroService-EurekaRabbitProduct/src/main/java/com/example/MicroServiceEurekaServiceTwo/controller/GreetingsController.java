package com.example.MicroServiceEurekaServiceTwo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GreetingsController {

	@GetMapping("/hello")
	public String greets() {
		return "greetz";
	}
	
}
