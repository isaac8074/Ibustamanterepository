package daos;

import models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOCommands implements AccountDAO {
    Connection connection;

    public AccountDAOCommands(Connection conn) {
        connection = conn;
    }

    @Override
    public List<Account> getAllClientAccounts(int customerID) {
        List<Account> accountList = new ArrayList<Account>();
        String sql = "SELECT * FROM accounts WHERE customerID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                int customerID2 = rs.getInt("customerID");
                float balance = rs.getFloat("balance");
                Account acc = new Account(accountID, customerID2, balance);
                accountList.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Account getClientAccountByID(int customerID, int accountID) {
        Account resultAccount = null;
        String sql = "SELECT * FROM accounts WHERE customerID = ? AND accountID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, accountID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int accountID2 = rs.getInt("accountID");
                int customerID2 = rs.getInt("customerID");
                float balance = rs.getFloat("balance");
                resultAccount = new Account(accountID2, customerID2, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAccount;
    }

    @Override
    public List<Account> getClientAccountsByMinAmt(int customerID, float amount) {
        List<Account> accountList = new ArrayList<Account>();
        String sql = "SELECT * FROM accounts WHERE customerID = ? AND balance > ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setFloat(2, amount);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                int customerID2 = rs.getInt("customerID");
                float balance = rs.getFloat("balance");

                Account acc = new Account(accountID, customerID2, balance);
                accountList.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    @Override
    public List<Account> getClientAccountsByMaxAmt(int customerID, float amount) {
        List<Account> accountList = new ArrayList<Account>();
        String sql = "SELECT * FROM accounts WHERE customerID = ? AND balance < ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setFloat(2, amount);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(pstmt.toString());
            System.out.println(rs.toString());

            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                int customerID2 = rs.getInt("customerID");
                float balance = rs.getFloat("balance");

                Account acc = new Account(accountID, customerID2, balance);
                accountList.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    @Override
    public List<Account> getClientAccountsByRange(int customerID, float minAmount, float maxAmount) {
        List<Account> accountList = new ArrayList<Account>();
        String sql = "SELECT * FROM accounts WHERE customerID = ? AND balance > ? AND balance < ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setFloat(2, minAmount);
            pstmt.setFloat(3, maxAmount);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                int customerID2 = rs.getInt("customerID");
                float balance = rs.getFloat("balance");

                Account acc = new Account(accountID, customerID2, balance);
                accountList.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    @Override
    public void createAccount(int customerID, float balance) {
        String sql = "INSERT INTO accounts (customerID, balance) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerID);
            pstmt.setFloat(2, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAccount(int accountID) {
        String sql = "DELETE FROM accounts WHERE accountID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, accountID);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deposit(int accountID, float balance) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE accountID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, balance);
            pstmt.setInt(2, accountID);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(int accountID, float balance) {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE accountID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, balance);
            pstmt.setInt(2, accountID);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int accountID, float amount) {
        String sql = "UPDATE accounts SET balance = ? WHERE accountID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, amount);
            pstmt.setInt(2, accountID);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
