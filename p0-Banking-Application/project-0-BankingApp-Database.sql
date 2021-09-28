
CREATE TABLE IF NOT EXISTS accounts
(
    accountID INT NOT NULL,
    customerID INT NOT NULL,
    balance DECIMAL (10, 2),
    CONSTRAINT accounts_pk PRIMARY KEY (accountID),
    CONSTRAINT customers_accounts_fk FOREIGN KEY (customerID) REFERENCES customers (customerID)
    );


CREATE TABLE IF NOT EXISTS customers
(
	customerID INT AUTO_INCREMENT,
	name VARCHAR(200),
    CONSTRAINT customerID_pk PRIMARY KEY (customerID)
    );

 	


INSERT INTO accounts (accountID, customerID, balance) VALUES (567890, 0001, 1500.50);
INSERT INTO accounts (accountID, customerID, balance) VALUES (684835, 0002, 10000.00);
INSERT INTO accounts (accountID, customerID, balance) VALUES (901345, 0002, 50.21);
INSERT INTO accounts (accountID, customerID, balance) VALUES (452566, 0002, 3644.66);
INSERT INTO accounts (accountID, customerID, balance) VALUES (645367, 0003, 9688.99);

INSERT INTO customers (customerID, name) VALUES (0001, "Jason Smith");
INSERT INTO customers (customerID, name) VALUES (0002, "Jose Bustamante");
INSERT INTO customers (customerID, name) VALUES (0003, "Harry Band");

SELECT * FROM accounts WHERE customerID = 2 AND balance < 5000

SELECT * FROM customers
SELECT * FROM accounts

DROP TABLE customers
DROP TABLE accounts