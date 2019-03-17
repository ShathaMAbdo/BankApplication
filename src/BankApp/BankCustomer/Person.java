package BankApp.BankCustomer;

import BankApp.Exceptions.LimitOfAccounts;

public class Person extends Customer {
    //Data fields
    public static final int maxAccounts=4;
    private int numberOfAccounts;

    // constructors
    public Person( String fname, String lname,String password) {
        super( fname, lname,password);
        this.setType(CustomerType.PERSON);
    }

    public Person( String fname, String lname,String password, String address, String phone) {
        super( fname, lname,password, address, phone);
        this.setType(CustomerType.PERSON);
    }

    //getNumberOfAcconts returns Number Of Accounts
    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    //setNumberOfAcconts sets Number Of Accounts
    public void setNumberOfAccounts(int numberOfAcconts) {
        this.numberOfAccounts = numberOfAcconts;
    }

    //uppdateMaxAccounts increase Number Of Accounts if it do not reach the maximum limit(maxAccounts)
    public void uppdateMaxAccounts(){
        if(!(numberOfAccounts+1>maxAccounts))
            numberOfAccounts++;
        else try {
            throw new LimitOfAccounts("The person");
        } catch (LimitOfAccounts e) {
            System.out.println(e.getMessage());
        }
    }
    @Override // toString returns string of persons data and calls super method tostring
    public String toString() {
        return "Person{" +
                super.toString()+
                ", number Of Accounts=" + numberOfAccounts +
                '}';
    }
}
