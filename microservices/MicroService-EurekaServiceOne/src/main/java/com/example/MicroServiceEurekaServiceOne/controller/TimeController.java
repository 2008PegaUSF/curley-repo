package com.example.MicroServiceEurekaServiceOne.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TimeController {

	
	@GetMapping("/ZAWARUDO")
	public String getTime() {
		return "TIME HAS STOPPED ON: " + new Date();
	}
}
