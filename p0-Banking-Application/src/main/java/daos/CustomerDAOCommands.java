package daos;

import models.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDAOCommands implements CustomerDAO {
    Connection connection;

    public CustomerDAOCommands(Connection conn) {
        connection = conn;
    }

    @Override
    public List<Customer> getAllClients() {
        List<Customer> clientList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customers";
        try {
            Statement smt = connection.createStatement();
            ResultSet rs = smt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                int customerID = rs.getInt("customerID");

                Customer customer = new Customer(name, customerID);
                clientList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public Customer getClientByID(int id) {
        Customer resultClient = null;
        String sql = "SELECT * FROM customers WHERE customerID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int customerID = rs.getInt("customerID");

                resultClient = new Customer(name, customerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultClient;
    }

    @Override
    public Customer getClientByName(String name) {
        Customer resultClient = null;
        String sql = "SELECT * FROM customers WHERE name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString("name");
                int customerID = rs.getInt("customerID");

                resultClient = new Customer(customerName, customerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultClient;
    }

    @Override
    public void createClient(String name) {
        String sql = "INSERT INTO customers (name) VALUES (?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(int id, String name) {
        String sql = "UPDATE customers SET name = ? WHERE customerID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int id) {
        String sql = "DELETE FROM customers WHERE customerID = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
