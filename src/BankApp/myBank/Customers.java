package BankApp.myBank;

import BankApp.BankCustomer.*;
import BankApp.Exceptions.NotFound;

import java.util.HashSet;

public class Customers {
    //Data field
    private HashSet<Customer> CList ;

    //constructor
    public Customers() {
        this.CList =new HashSet<>();
    }

    //getCList method returns HashSet of Customers
    public HashSet<Customer> getCList() {
        return CList;
    }

    //getCList method sets HashSet of Customers
    public void setCList(HashSet<Customer> CList) {
        this.CList = new HashSet<>(CList);
    }

    //findCustomer method finds customer that id equal input value
    public Customer findCustomer(long customerId) {
        for (Customer temp : CList) {
            if (temp.getPersonId() == customerId)
                return temp;
        }
            try {
                throw new NotFound("Do Not find Customer ");
            } catch (NotFound e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

    // add method adds customer to list of customers
    public void add(Customer customer) {
        if(customer!=null)
               this.CList.add(customer);
    }

    // uppdate method uppdates data of customer
    public boolean uppdate(long customerId,String type, String fname, String lname,String address, String phon) {
        for (Customer c : CList) {
            if (c.getPersonId() == customerId) {
                c.uppdate(type,fname,lname,address,phon);
                return true;
            }

        } return false;
    }

    // ispresent methods checks if input customer object present in the customers list
    public boolean ispresent(Customer customer){
        if (!CList.contains(customer))return true;
        return false;
    }

    @Override // method returen string of all customers
    public String toString() {
        return "Customers{" +
                "CList=" + CList +
                '}';
    }
}
