package model;

public class Account {
	
	/**
	 * 
	 */
	private int customerId, accountId;
	private double balance;

	public Account(int accountId, int customerId, double balance) {
		this.accountId = accountId;
		this.balance = balance;
		this.customerId = customerId;
	}



	public int getAccountId() {
		return this.accountId;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public String toString() {
		return "Account ID: " + accountId + " Customer ID: " + customerId + " Balance: $" + balance;
	}


	
}
