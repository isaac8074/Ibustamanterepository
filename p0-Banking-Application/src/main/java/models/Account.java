package models;

import java.util.Objects;

public class Account {
    private int accountID;
    private float balance;
    private int customerID;

    public Account() {
        super();
    }

    public Account(int accountID, int customerID, float balance)    {
        super();
        this.accountID = accountID;
        this.customerID = customerID;
        this.balance = balance;
    }

    public int getAccountID()   {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode()   {
        return Objects.hash(accountID, balance, customerID);
    }

    @Override
    public boolean equals(Object obj)   {
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Account other = (Account) obj;
        return accountID == other.accountID && Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance) && customerID == other.customerID;
    }

    @Override
    public String toString()    {
        return "Accounts [accountID=" + accountID + ", customerID=" + customerID + ", balance=" + balance + "]";
    }
}