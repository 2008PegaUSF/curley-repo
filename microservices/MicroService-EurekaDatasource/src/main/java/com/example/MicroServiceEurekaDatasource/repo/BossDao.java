package com.example.MicroServiceEurekaDatasource.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.MicroServiceEurekaDatasource.model.Boss;

public interface BossDao extends CrudRepository<Boss, Integer>{

	public List<Boss> findAll();
	public Boss findById(int id);
	public Boss findByName(String name);
	public Boss save(Boss b);
}
