package com.example.MicroServiceEurekaServiceThree.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ImageController {

	@GetMapping(value = "/getAnImage", produces = "text/html")
	public String getImage() {
		return "<img src=\"https://catcssp2imgbucket.s3.us-east-2.amazonaws.com/1602197924119attackdog.jpg\">";
	}
	
}
