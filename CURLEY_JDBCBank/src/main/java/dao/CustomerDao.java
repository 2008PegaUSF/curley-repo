package dao;

import java.util.ArrayList;

import model.Customer;

public interface CustomerDao {
	public ArrayList<Customer> getAllCustomers();
	public Customer getCustomerByUsername(String username);
	public Customer getCustomerByUserId(int id);
	public int createNewCustomer(String firstname, String lastname, int userid);
	public String updateCustomerInformation(int customerId, String firstname, String lastname);
	public void deleteCustomerByCustomerId(int customerId);
	public Customer getCustomerByCustomerId(int customerId);
}
