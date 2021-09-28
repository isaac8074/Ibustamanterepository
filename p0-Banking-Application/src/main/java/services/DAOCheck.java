package services;

import daos.CustomerDAOCommands;
import daos.AccountDAOCommands;

public class DAOCheck {
    public static boolean CustomerExists(CustomerDAOCommands customer, int id)    {
        if(customer.getClientByID(id)==null)    {
            return false;
        }
        return true;
    }
    public static boolean AccountExists(AccountDAOCommands account, int customerID, int accountID)    {
        if(account.getClientAccountByID(customerID, accountID) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean InsufficientFunds(float balance, float amount)    {
        if((balance-amount) < 0)    {
            return true;
        }
        else    {
            return false;
        }
    }
}
