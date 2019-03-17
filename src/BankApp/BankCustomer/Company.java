package BankApp.BankCustomer;

import BankApp.Exceptions.LimitOfAccounts;

public class Company extends Customer {

    public static final int maxAccounts=10;
    private int numberOfAccounts=0;

    public Company(String fname, String lname,String password) {
        super(fname, lname,password);
        this.setType(CustomerType.COMPANY);
            }

    public Company(String fname, String lname,String password, String address, String phone) {
        super(fname, lname,password, address, phone);
        this.setType(CustomerType.COMPANY);
    }
    //getNumberOfAcconts returns Number Of Accounts
    public int getNumberOfAcconts() {
        return numberOfAccounts;
    }

    //setNumberOfAcconts sets Number Of Accounts
    public void setNumberOfAcconts(int numberOfAcconts) {
        this.numberOfAccounts = numberOfAcconts;
    }

    //uppdateMaxAccounts increase Number Of Accounts if it do not reach the maximum limit(maxAccounts)
    public void uppdateMaxAccounts(){
        if(!(numberOfAccounts+1>maxAccounts))
            numberOfAccounts++;
        else try {
            throw new LimitOfAccounts("The Company");
        } catch (LimitOfAccounts e) {
           System.out.println(e.getMessage());
        }
    }
    @Override // toString returns string of companys data and calls super method tostring
    public String toString() {
        return "Company{" +
                super.toString()+
                ", number Of Accounts= " + numberOfAccounts +
                '}';
    }
}
