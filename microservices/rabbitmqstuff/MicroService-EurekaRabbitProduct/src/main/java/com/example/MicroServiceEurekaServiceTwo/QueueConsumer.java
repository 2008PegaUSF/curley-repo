package com.example.MicroServiceEurekaServiceTwo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Component;

import com.example.MicroServiceEurekaServiceTwo.model.Order;
import com.example.MicroServiceEurekaServiceTwo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueConsumer {

	@Autowired
	ProductService service;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private ObjectMapper mapper = new ObjectMapper();
	
	public void receiveOrder(String message) {
		logger.info("received message " + message);
		processMessage(message);
	}
	
	public void receiveOrder(byte[] message) {
		String strMessage = new String(message);
		processMessage(strMessage);
	}
	
	private void processMessage(String message) {
		try {
			logger.info("Attempting to parse... " + message);
			Order order = (Order) mapper.readValue(message, Order.class);
			logger.info("Order is " + order.toString());
			service.process(order);
		} catch (JsonParseException e) {
			logger.warn("bad msg" + message);
		} catch (JsonMappingException e) {
			logger.warn("cannot map JSON to NotificationReuqest: " + message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());;
		}
	}
}
