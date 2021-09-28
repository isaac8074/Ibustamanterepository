package models;

import java.util.Objects;

public class Customer {
    private int customerID;
    private String name;

    public Customer() {
        super();
    }
    public Customer(String name, int customerID)   {
        super();
        this.name = name;
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode()   {
        return Objects.hash(customerID, name);
    }

    @Override
    public boolean equals(Object obj)   {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return Objects.equals(customerID, other.customerID) && Objects.equals(name, other.name);
    }

    @Override
    public String toString()    {
        return "client [name=" + name + ", customerID=" + customerID + "]";
    }
}