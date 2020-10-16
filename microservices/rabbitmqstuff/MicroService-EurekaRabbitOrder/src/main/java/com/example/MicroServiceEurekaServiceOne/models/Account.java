package com.example.MicroServiceEurekaServiceOne.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="account_table")
public class Account {

	@Id
	@Column(name="account_id")
	private Long id;
	@Column(name="account_number")
	private String number;
	@Column(name="account_balance")
	private int balance;
	@Column(name="customer_id")
	private Long customerId;

	
	public Account(String number, int balance, Long customerId) {
		this.number = number;
		this.balance = balance;
		this.customerId = customerId;
	}


}
