package com.example.MicroServiceEurekaServiceThree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;

import com.example.MicroServiceEurekaServiceThree.model.Account;
import com.example.MicroServiceEurekaServiceThree.model.Order;
import com.example.MicroServiceEurekaServiceThree.repository.AccountRepository;
import com.example.MicroServiceEurekaServiceThree.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import brave.http.HttpSampler;
import brave.sampler.Sampler;

@EnableBinding(Processor.class)
@SpringBootApplication
public class MicroServiceEurekaServiceThreeApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceEurekaServiceThreeApplication.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	AccountService service;
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEurekaServiceThreeApplication.class, args);
	}

	
	@StreamListener(Processor.INPUT)
	public void receiveOrder(Order order) throws JsonProcessingException {
		LOGGER.info("Order received: {}", mapper.writeValueAsString(order));
		service.process(order);
	}
	
	@Bean
	AccountRepository repository() {
		AccountRepository repository = new AccountRepository();
		repository.add(new Account("1234567890", 50000, 1L));
		repository.add(new Account("1234567891", 50000, 1L));
		repository.add(new Account("1234567892", 0, 1L));
		repository.add(new Account("1234567893", 50000, 2L));
		repository.add(new Account("1234567894", 0, 2L));
		repository.add(new Account("1234567895", 50000, 2L));
		repository.add(new Account("1234567896", 0, 3L));
		repository.add(new Account("1234567897", 50000, 3L));
		repository.add(new Account("1234567898", 50000, 3L));
		return repository;
	}

	/*@Bean
	public Sampler defaultSampler() {
		return new HttpSampler();
	}*/
	
}
