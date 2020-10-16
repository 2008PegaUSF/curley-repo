package com.example.MicroServiceEurekaServiceOne.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroServiceEurekaServiceOne.QueueProducer;
import com.example.MicroServiceEurekaServiceOne.messaging.OrderStatus;
import com.example.MicroServiceEurekaServiceOne.models.Order;
import com.example.MicroServiceEurekaServiceOne.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	private QueueProducer qp;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	OrderRepository repository;
	
	public void process(final Order order) throws JsonProcessingException {
		LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
		Order o = repository.findById(order.getId());
		if (o.getStatus() != OrderStatus.REJECTED) {
			o.setStatus(order.getStatus());
			repository.update(o);
			LOGGER.info("Order status updated: {}", mapper.writeValueAsString(order));
		}
	}
	
}
