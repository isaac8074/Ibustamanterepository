package daos;


import models.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    public List<Customer> getAllClients();
    public Customer getClientByName(String name);
    public Customer getClientByID(int id);
    public void createClient(String name);
    public void updateClient(int id, String name);
    public void deleteClient(int id);
}