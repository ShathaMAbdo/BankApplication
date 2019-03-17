package BankApp.myBank;

import BankApp.BankAccounts.*;
import BankApp.BankCustomer.*;

import BankApp.Exceptions.*;

import java.util.*;

public class MyBank {
    // Data field
    List<CustomerAccount> myCustAcc;
    private Accounts accounts;
    private Customers customers;

    //constructor
    public MyBank() {
        this.myCustAcc = new ArrayList<>();
        this.accounts = new Accounts();
        this.customers = new Customers();
    }

    //method getMyCustAcc returns myCustAcc
    public List<CustomerAccount> getMyCustAcc() {
        return myCustAcc;
    }

    //method setMyCustAcc sets myCustAcc
    public void setMyCustAcc(List<CustomerAccount> myCustAcc) {
        this.myCustAcc = myCustAcc;
    }

    //getAccounts methods returns accounts
    public Accounts getAccounts() {
        return accounts;
    }

    //setAccounts methods sets accounts
    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    //getCustomers methods returns Customers
    public Customers getCustomers() {
        return customers;
    }

    //setCustomers methods sets customers
    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    // newCustAcc methods creates new object of Custommer with account and adds it to list
    public void newCustAcc(Customer customer, BankAccount account) {
        this.getCustomers().getCList().add(customer);
        this.getAccounts().getaList().add(account);
        CustomerAccount newCustAcc = new CustomerAccount(customer.getPersonId(), account.getAccountNo());
        for (CustomerAccount ca : this.getMyCustAcc()) {
            if (newCustAcc.equals(ca))
                try {
                    throw new DublecateIntry();
                } catch (DublecateIntry e) {
                    System.out.println(e.getMessage());
                }
        }
        this.getMyCustAcc().add(newCustAcc);
        if (customer instanceof Person)
            ((Person) customer).uppdateMaxAccounts();
        else
            ((Company) customer).uppdateMaxAccounts();

    }

    // removeC_A_ByCust methods resets all accounts for customer
    public void removeC_A_ByCust(int customerId) {
        List<CustomerAccount> result = searchByCust(customerId);
        BankAccount account;
        if (result.size() != 0) {
            for (CustomerAccount temp : myCustAcc) {
                account = accounts.findAccount(temp.getAccountId());
                account.remove();
                myCustAcc.remove(temp);
            }
        }
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println(" this Customet whoes id is " + customerId + "has not any account");
        }
    }

    // removeC_A_ByCust methods resets accounts for a specific account
    public void removeC_A_ByAccount(long accountId) {
        List<CustomerAccount> result = searchByAccount(accountId);
        BankAccount account;
        if (result.size() != 0) {
            for (CustomerAccount temp : myCustAcc) {
                account = accounts.findAccount(temp.getAccountId());
                account.remove();
                myCustAcc.remove(temp);
            }
        } else
            try {
                throw new AccountNotFound();
            } catch (AccountNotFound e) {
                System.out.println(" this Account whoes id is " + accountId + e.getMessage());
            }
    }

    private List<CustomerAccount> searchByAccount(long accountId) {
        List<CustomerAccount> list = new ArrayList<>();
        for (CustomerAccount temp : myCustAcc) {
            if (temp.getAccountId().equals(accountId))
                list.add(temp);
        }
        return list;
    }
    // searchByCust methods search accounts for customer
    private List<CustomerAccount> searchByCust(long customerId) {
        List<CustomerAccount> list = new ArrayList<>();
        for (CustomerAccount temp : myCustAcc) {
            if (temp.getCustomerId().equals(customerId))
                list.add(temp);
        }
        return list;
    }
     // printAccountsForCustomer method prints all acounts for customer
    public void printAccountsForCustomer(long customerId) throws AccountNotActive {
        Customer c = customers.findCustomer(customerId);
        System.out.print("This customer :  " + c + " \n");
        System.out.println();
        List<CustomerAccount> list = searchByCust(customerId);
        if (list.size() != 0) {
            System.out.print("Has these accounts : \n");
            for (CustomerAccount temp : list) {
                BankAccount account = accounts.findAccount(temp.getAccountId());
                if (account.isActive()) {
                    System.out.println(account);
                }
            }
            System.out.print("------------------------------------------------\n");
        } else
            try {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                System.out.println(" this Customet whoes id is " + customerId + "has not any account");
            }
    }

    //printCustomerForAccount methods prints all customers for a common account
    public void printCustomerForAccount(long AccountId) throws AccountNotActive, NotValidation {
        BankAccount b = accounts.findAccount(AccountId);
        if (b.isActive()) {
            System.out.print("The customers whiche has this account " + b + " are :\n");
            List<CustomerAccount> list = searchByAccount(AccountId);
            if (list.size()==0)  throw new NotValidation("Uncorrect common account number");
            if(list.size()==1)   throw new NotValidation("This account is not common account");
            for (CustomerAccount temp : list) {
                Customer c = customers.findCustomer(temp.getCustomerId());
                System.out.println(c);
                System.out.print("-------------------------------------------------\n");
            }
        } else try {
            throw new AccountNotActive();
        } catch (AccountNotActive e) {
            System.out.println("Account Id = " + AccountId + "  " + e.getMessage());
        }

    }

    //printAll methods print all accounts - customers
    public void printAll() throws AccountNotActive {
        if (!(myCustAcc == null))
            for (CustomerAccount temp : myCustAcc) {
                if (accounts.findAccount(temp.getAccountId()).isActive()) {
                    System.out.println(customers.findCustomer(temp.getCustomerId()));
                    System.out.println(accounts.findAccount(temp.getAccountId()));
                    System.out.print("-------------------------------------------------\n");
                }
            }
        else
            try {
                throw new NullPointerException("There are not Account yet");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
    }

    //findMyCustAcc methods find object of CustomerAccount by customer id and account id
    public CustomerAccount findMyCustAcc(long customeId, long accountNo) throws NotFound {
        List<CustomerAccount> myaccounts = this.searchByCust(customeId);
        List<CustomerAccount> entry = this.searchByAccount(accountNo);
        if (entry.size() == 1)
            return entry.get(0);
        throw new NotFound("Entr not Found");
    }

    // transfer method drars amount from sender bank account and credit this amount in receiver bankaccount
    public boolean transfer(BankAccount sender, BankAccount receiver, double amount) throws AccountNotActive, NotValidation {
        if (sender.debit(amount)&&(receiver.credit(amount)))
                return true;
         return false;

    }

}
