package com.example.MicroServiceZuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.MicroServiceZuul.filter.PostFilter;

@Configuration
public class Configs {

	
	@Bean
	public PostFilter filter() {
		return new PostFilter();
	}
}
