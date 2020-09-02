package model;

public class Customer {


	
	private String firstName, lastName;
	private int userId, customerId;
	
	public Customer(int customerId, String firstName, String lastName, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.customerId = customerId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getUserId() {
		return userId;
	}

	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int id) {
		customerId = id;
	}
	
	public String toString() {
		return "Name: " + firstName + " " + lastName + " User ID: " + userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
		
	}

}
