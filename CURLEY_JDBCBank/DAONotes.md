DAO Methods

UserDAO
TransactionDAO
CustomerDAO
AccountDAO


Relations

Users
**************************
Traits
**************************
Holds Username and Password for each account
Has an ID
ID is Used to match a Customer or the Admin to a username and password

----------------------------
Functionality
----------------------------
//////////////////
////ADMIN/ONLY////
//////////////////
List<user> getAllUsers(User)
Returns a list of all users, performs a hard check to ensure only an administrator is performing this action
if not admin user, then just say "Invalid Account, how'd you get here?". Returns null if so.

\\\\\\\\\\\\\\\\\\
\\\OTHER\\USERS\\\
\\\\\\\\\\\\\\\\\\
User getUser(String username)
Returns one User or null, dependent on the username matches.  Used for user authentication.


==============================================================================
Account
**************************
Traits
**************************
Has its own ID
Has a balance
Has a CustomerID field used to associate this Account with its assigned Customer
----------------------------
Functionality
----------------------------
//////////////////
////ADMIN/ONLY////
//////////////////
List<Account> getAllAccounts(user)
returns a list of all accounts, performs a hard check that admin is calling this function.

\\\\\\\\\\\\\\\\\\
\\\OTHER\\USERS\\\
\\\\\\\\\\\\\\\\\\

==============================================================================
Customer
**************************
Traits
**************************
Has ID CustomerID which is used mostly for refent to the Account class.
Has FirstName, LastName fields.
References UserID in Users to facilitate customer/username association

----------------------------
Functionality
----------------------------
//////////////////
////ADMIN/ONLY////
//////////////////
List<Customer> getAllCustomer(user)
See above lists as functionality is similar

\\\\\\\\\\\\\\\\\\
\\\OTHER\\USERS\\\
\\\\\\\\\\\\\\\\\\
Customer getCustomerByUsername(String username)
gets a customer by a supplied username.
==============================================================================
Transaction
**************************
Traits
**************************
Has identifier TransactionID
Has int for type of transaction (withdrawal/deposit)
Has Date for when transaction occurred occurred
Has int for AccountID performed on
Has double for Amount applied
Has double for Amount post-transaction

Interacts with the Transaction objects
----------------------------
Functionality
----------------------------
