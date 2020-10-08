package com.example.MicroServiceEurekaFailsafe.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.MicroServiceEurekaFailsafe.model.Boss;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BossService {

	
	private RestTemplate restTemplate;
	
	public BossService(RestTemplate restTemplate) {
		super();
		this.restTemplate=restTemplate;
	}
	
	@HystrixCommand(fallbackMethod = "getDownIndividual")
	public Boss getBossByName(String value) {
		URI uri = URI.create("http://localhost:8100/boss/" + value);
		Boss b = this.restTemplate.getForObject(uri, Boss.class);
		return b;
	}
	
	@HystrixCommand(fallbackMethod = "getDownIndividual")
	public Boss getBossById(int id) {
		URI uri = URI.create("http://localhost:8100/boss/" + id);
		Boss b = this.restTemplate.getForObject(uri, Boss.class);
		return b;
	}
	
	public Boss getDownIndividual(String value) {
		return new Boss(0, "The Sleeping Lord", 9001, "https://media.discordapp.net/attachments/760236401471127554/763497987409444894/unknown.png");
	}
	
	public Boss getDownIndividual(int id) {
		return getDownIndividual("");
	}
	
	@HystrixCommand(fallbackMethod = "getDownList")
	public List<Boss> getBossList() {
		URI uri = URI.create("http://localhost:8100/boss/");
		List<Boss> bosses = (List<Boss>) this.restTemplate.getForObject(uri, List.class);
		//bosses.add(new Boss(0, "The Sleeping Lord", 9001, "https://media.discordapp.net/attachments/760236401471127554/763497987409444894/unknown.png"));
		return bosses;
	}
	
	public List<Boss> getDownList() {
		List<Boss> list = new ArrayList<>();
		list.add(new Boss(0, "DOOR STUCK, DOOR STUCK!", -1, "the server for the content you are looking for is down :("));
		return list;
	}
}
