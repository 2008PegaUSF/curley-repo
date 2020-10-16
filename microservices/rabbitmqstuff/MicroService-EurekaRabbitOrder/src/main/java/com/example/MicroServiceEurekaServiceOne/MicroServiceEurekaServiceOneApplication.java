package com.example.MicroServiceEurekaServiceOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.example.MicroServiceEurekaServiceOne.models.Order;
import com.example.MicroServiceEurekaServiceOne.repository.OrderRepository;
import com.example.MicroServiceEurekaServiceOne.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableBinding(Processor.class)
public class MicroServiceEurekaServiceOneApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceEurekaServiceOneApplication.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	OrderService service;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEurekaServiceOneApplication.class, args);
	}
	
	@StreamListener(Processor.INPUT)
	public void receiveOrder(Order order) throws JsonProcessingException {
		LOGGER.info("Order received: {}",mapper.writeValueAsString(order));
		
	}
	
	@Bean
	OrderRepository repository() {
		return new OrderRepository();
	}
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		loggingFilter.setMaxPayloadLength(1000);
		loggingFilter.setAfterMessagePrefix("REQ: ");
		return loggingFilter;
	}

}
