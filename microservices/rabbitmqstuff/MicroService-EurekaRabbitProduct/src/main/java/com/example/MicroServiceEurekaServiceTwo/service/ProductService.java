package com.example.MicroServiceEurekaServiceTwo.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroServiceEurekaServiceTwo.messaging.OrderSender;
import com.example.MicroServiceEurekaServiceTwo.messaging.OrderStatus;
import com.example.MicroServiceEurekaServiceTwo.model.Order;
import com.example.MicroServiceEurekaServiceTwo.model.Product;
import com.example.MicroServiceEurekaServiceTwo.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderSender orderSender;
	
	public void process(final Order order) throws JsonProcessingException {
		LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
		for (Long productId : order.getProductIds()) {
			Product product = productRepository.findById(productId);
			if (product.getCount() == 0) {
				order.setStatus(OrderStatus.REJECTED);
				break;
			}
			product.setCount(product.getCount() - 1);
			productRepository.update(product);
			LOGGER.info("Product updated: {}", mapper.writeValueAsString(product));
		}
		if (order.getStatus() != OrderStatus.REJECTED) {
			order.setStatus(OrderStatus.ACCEPTED);
		}
		LOGGER.info("Order response sent: {}", mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
		orderSender.send(order);
	}	
	
}
