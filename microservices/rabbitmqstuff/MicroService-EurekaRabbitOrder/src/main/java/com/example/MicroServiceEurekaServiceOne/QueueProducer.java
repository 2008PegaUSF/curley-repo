package com.example.MicroServiceEurekaServiceOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.MicroServiceEurekaServiceOne.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueProducer {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${fanout.exchange}")
	private String fanoutExchange;
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate=rabbitTemplate;
	}
	
	public void produce(Order notificationDTO) {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("SENDING");
		logger.info("storing message");
		rabbitTemplate.setExchange(fanoutExchange);
		try {
			rabbitTemplate.convertAndSend(mapper.writeValueAsString(notificationDTO));
		} catch (AmqpException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
	}
	
}
