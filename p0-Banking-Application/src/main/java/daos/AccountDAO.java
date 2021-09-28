package daos;

import models.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> getAllClientAccounts(int customerID);
    public Account getClientAccountByID(int customerID, int accountID);
    public List<Account> getClientAccountsByMinAmt(int customerID, float amount);
    public List<Account> getClientAccountsByMaxAmt(int customerID, float amount);
    public List<Account> getClientAccountsByRange(int customerID, float minAmount, float maxAmount);
    public void createAccount(int customerID, float balance);
    public void deleteAccount(int accountID);
    public void deposit(int accountID, float balance);
    public void withdraw(int accountID, float balance);
    public void update(int accountID, float amount);
}