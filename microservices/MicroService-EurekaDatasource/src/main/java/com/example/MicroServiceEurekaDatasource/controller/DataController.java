package com.example.MicroServiceEurekaDatasource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroServiceEurekaDatasource.model.Boss;
import com.example.MicroServiceEurekaDatasource.service.DataService;

@RestController
@CrossOrigin
@RequestMapping("/boss")
public class DataController {

	@Autowired
	private DataService ds;
	
	@GetMapping("/")
	public List<Boss> getAllBosses() {
		return ds.getAll();
	}
	
	@GetMapping("/{value}")
	public Boss getBossById(@PathVariable("value") String value) {
		if(value.matches("[0-9]*")) {
			int id = Integer.parseInt(value);
			return ds.getBossById(id);
		} else {
			return ds.getBossByName(value);
		}
	}
	
	@PostMapping(value = "/create", consumes = "application/json")
	public String createNewBoss(@RequestBody Boss b) {
		ds.createBoss(b);
		return "SUCCESS";
	}
}
