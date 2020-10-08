package com.example.MicroServiceEurekaDatasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroServiceEurekaDatasource.model.Boss;
import com.example.MicroServiceEurekaDatasource.repo.BossDao;

@Service
public class DataService {
	
	@Autowired
	private BossDao bd;
	
	public List<Boss> getAll() {
		return bd.findAll();
	}
	
	public Boss getBossById(int id) {
		return bd.findById(id);
	}
	
	public String createBoss(Boss b) {
		bd.save(b);
		return "SUCCESS: ADDED BOSS" + b.toString();
	}
	
	public Boss getBossByName(String name) {
		return bd.findByName(name);
	}
}
