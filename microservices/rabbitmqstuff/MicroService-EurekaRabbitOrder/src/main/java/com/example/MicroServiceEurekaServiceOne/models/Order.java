package com.example.MicroServiceEurekaServiceOne.models;

import java.util.List;

import com.example.MicroServiceEurekaServiceOne.messaging.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="order_table")
public class Order {

//	@Id
//	@Column(name="order_id")
	private Long id;
//	@Column(name="order_status")
	private OrderStatus status;
//	@Column(name="order_price")
	private int price;
//	@Column(name="customer_id")
	private Long customerId;
//	@Column(name="account_id")
	private Long accountId;
//	@Column(name="product_ids")
	private List<Long> productIds;

}
