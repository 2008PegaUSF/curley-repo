package com.example.MicroServiceEurekaFailsafe.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Boss {

	private int id;

	private String name;

	private int health;

	private String image;
}
