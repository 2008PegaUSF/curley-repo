package com.example.MicroServiceEurekaServiceThree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroServiceEurekaServiceThree.messaging.OrderSender;
import com.example.MicroServiceEurekaServiceThree.messaging.OrderStatus;
import com.example.MicroServiceEurekaServiceThree.model.Account;
import com.example.MicroServiceEurekaServiceThree.model.Order;
import com.example.MicroServiceEurekaServiceThree.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	OrderSender orderSender;
	
	public void process(final Order order) throws JsonProcessingException {
		LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
		List<Account> accounts =  accountRepository.findByCustomer(order.getCustomerId());
		Account account = accounts.get(0);
		LOGGER.info("Account found: {}", mapper.writeValueAsString(account));
		if (order.getPrice() <= account.getBalance()) {
			order.setStatus(OrderStatus.ACCEPTED);
			account.setBalance(account.getBalance() - order.getPrice());
		} else {
			order.setStatus(OrderStatus.REJECTED);
		}
		orderSender.send(order);
		LOGGER.info("Order response sent: {}", mapper.writeValueAsString(order));
	}
	
}
