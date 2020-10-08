package com.example.MicroServiceEurekaFailsafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MicroServiceEurekaFailsafe.model.Boss;
import com.example.MicroServiceEurekaFailsafe.service.BossService;

@RestController
@RequestMapping("/public")
public class SafeController {

	@Autowired
	private BossService bs;
	
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@GetMapping("/boss")
	public List<Boss> getAllBosses() {
		return bs.getBossList();
	}
	
	@GetMapping("/boss/{value}")
	public Boss getBossByValue(@PathVariable("value") String value) {
		if(value.matches("[0-9]")) {
			int id = Integer.parseInt(value);
			return bs.getBossById(id);
		} else {
			return bs.getBossByName(value);
		}
	}
}
