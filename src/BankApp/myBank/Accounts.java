package BankApp.myBank;

import BankApp.BankAccounts.*;
import BankApp.Exceptions.NotFound;

import java.util.HashSet;

public class Accounts {
    //Data field
    private HashSet<BankAccount> aList;

    //constructor
    public Accounts() {
        this.aList  =new HashSet<>();
    }

    //getaList returns HashSet Of Accounts
    public HashSet<BankAccount> getaList() {
        return aList;
    }

    //setaList sets HashSet Of Accounts
    public void setaList(HashSet<BankAccount> aList) {
        this.aList = new HashSet<>(aList);
    }

    //findAccount method returns account by input account number
    public BankAccount findAccount(long accountId) {
        for (BankAccount temp : aList) {
            if (temp.getAccountNo() == accountId)
                return temp;
        }
        try {
            throw new NotFound("Do Not find Account ");
        } catch (NotFound e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //add method adds accounts to accounts list
    public void add(BankAccount account) {
        if(account!=null)
            this.aList.add(account);
    }

    //remove method calling findAccount method for find account by id then set account inactive
    public boolean remove(long accountId) {
        BankAccount temp = findAccount(accountId);
        if (temp != null) {
            temp.setActive(false);
            return true;
        }
        return false;
    }

    //ispresent method returns true if account present in accounts list
    public boolean ispresent(BankAccount account){
        if (!aList.contains(account))return true;
        return false;
    }

    @Override // toString method returns  data of all accounts
    public String toString() {
        return "Accounts{" +
                "aList=" + aList +
                '}';
    }
}
