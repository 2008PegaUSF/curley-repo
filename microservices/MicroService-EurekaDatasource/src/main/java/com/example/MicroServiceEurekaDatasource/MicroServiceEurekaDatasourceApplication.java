package com.example.MicroServiceEurekaDatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroServiceEurekaDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEurekaDatasourceApplication.class, args);
	}

}
