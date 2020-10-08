package com.example.MicroServiceEurekaDatasource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bosses")
public class Boss {

	@Id
	@Column(name="boss_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="health", nullable = false)
	private int health;
	
	@Column(name="image", nullable = false)
	private String image;
}
