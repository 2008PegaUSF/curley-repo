
# CURLEY_JDBCBank

This project is an improvement over the previous implementation.  It contains:

  - More meaningful user feedback
  - Better validation
  - Custom Exceptions
  - JDBC Connectivity

### To start this program, run mainapp/App.java

# Before using this software, ensure that you run JDBCBank.sql in a PostgreSQL compatible context

# About Administrator login

 Administrator account information is stored locally, but still requires validation
 
### Adminstrator information
- username: superuser
- password: r00+U$32

## How to Log in as the Administrator
The initial menu looks like this:
```sh
Please select from one of the following options:
Enter 1 for customer actions.
Enter 2 for administrator actions.
Enter 3 to exit
```
Choose option 2.  You will then be greeted by a similar screen:
```sh
2
Entering adminstrator mode...
Please enter administrator username. (3 attempt(s) remaining.)
superuser
Username accepted.
Please enter administrator password
```

Enter the password and then you will be greeted with this menu. Each option inside of this menu will offer many helpful prompts along the way.
```sh
Please select from one of the following options:
Enter 1 view users.
Enter 2 create users.
Enter 3 assign bank accounts to users.
Enter 4 update users.
Enter 5 for delete operations.
Enter 6 to log out and go back to the main menu
[ADMIN] Admin>> 
```
### Logging in as a Customer
The process for logging in as a customer is very similar:
```sh
Enter 1 for customer actions.
Enter 2 for administrator actions.
Enter 3 to exit
```
Enter 1 to log in, which will then greet you with this prompt
```sh
Entering customer mode...
You are logging in as a customer.
Please enter 1 to sign in with an existing account, or enter 2 to sign up for a new account. Press 3 to go back to the main menu.
```

Entering 1 will follow with this series of prompts:
```sh
Checking for existing customer accounts...
Found customer information.
Please enter your username
billy1
Please enter your password
-------
```
After successful login, a similar menu is shown for customer options when a customer has a valid account attached:
```sh
Authorized.
Welcome back, Billy.
Account information: 
1. Account ID: 4 Customer ID: 2 Balance: $65.0
Please choose from one of the following options: 
Enter 1 to view accounts and balances.
Enter 2 to delete an account with zero balance.
Enter 3 to create an account.
Enter 4 to deposit funds into an account.
Enter 5 to withdraw funds from an account.
Enter 6 to sign out.
```

Each option chosen will have meaningful, helpful prompts throughout the duration of use.
