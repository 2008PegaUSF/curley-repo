package dao;

import java.util.ArrayList;

import model.Account;

public interface AccountDao {
	public ArrayList<Account> getAllAccounts();
	public ArrayList<Account> getAllAccountsByCustomerId(int id);
	public Account getAccountByAccountId(int id);
	public int createNewAccount(int customerId);

	public double updateAccountBalance(int accountId, double amount);
	public ArrayList<Account> getAllAccountsByBalance(int customerId, double balance);
	public void deleteAnAccountByAccountId(int accountId);
	public void deleteAllAccountsByCustomerId(int customerId);
}
