package model;

public class Transaction {

	private char type;
	private double accountPreBalance;
	private double amountTaken;
	private Account accountPerformed;
	private Account accountAffected;
	private String whoInitiated;
	
	public Transaction(char t, Account accountperformed, Account accountaffected, double accountprebalance, double amounttaken, String who) {
		type = t;
		if(t != 't') accountaffected = accountperformed;
		accountPerformed = accountperformed;
		accountAffected = accountaffected;
		accountPreBalance = accountprebalance;
		amountTaken = amounttaken;
		whoInitiated = who;
	}
	
	private String getType(char c) {
		switch(c) {
		case 'w':
			return "Withdrawal";

		case 'd':
			return "Deposit";

		case 't':
			return "Transfer";

		}
		return "Unknown";
	}
	
	public String toString() {
		return String.format("%nTransaction type: %s%nAccount ID Initiating action: %d%n" +
				"Account ID Affected by action: %d%nBalance before action occurred: $%.2f%n" +
				"Amount Applied: $%.2f%nAmount Post Action: $%.2f%nUsername Initiated: %s", 
				getType(type), accountPerformed.getAccountId(), accountAffected.getAccountId(), 
				accountPreBalance,amountTaken,accountAffected.getBalance(), whoInitiated
				);
	}
	
	
}
