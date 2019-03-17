package BankApp.BankAccounts;

import BankApp.BankTransaction.Transaction;
import BankApp.BankTransaction.TransactionType;
import BankApp.Exceptions.AccountNotActive;
import BankApp.Exceptions.ExceedOverdraft;
import BankApp.Exceptions.NotValidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    //Data fields
    private double currentBalance;
    private long AccountNo;
    private AccountType type;
    private boolean active;
    private double charges;// This amounts of charges for one year.
    protected static long count;
    private List<Transaction> transactList;
    private LocalDate openDate;

    //Constructor

    public BankAccount(double Balance) {
        this.currentBalance = Balance;
        transactList = new ArrayList<>();
        this.active = true;
        openDate = LocalDate.now();
    }//end of Constructor

    //getCurrentBalance method returens currentBalance field
    public double getCurrentBalance() {
        return currentBalance;
    }

    //setCurrentBalance method sets currentBalance field
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    //getType method returens type field
    public AccountType getType() {
        return type;
    }

    //setType method sets type field
    public void setType(AccountType type) {
        this.type = type;
    }

    //getAccountNo method returens AccountNo field
    public long getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(long accountNo) {
        AccountNo = accountNo;
    }

    //getTransactList method returens transactList field
    public List<Transaction> getTransactList() {
        return transactList;
    }

    //setTransactList method sets transactList field
    public void setTransactList(List<Transaction> transactList) {
        this.transactList = transactList;
    }

    //getOpenDate method returens openDate field
    public LocalDate getOpenDate() {
        return openDate;
    }

    //setOpenDate method sets openDate field
    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    //isActive methods returens active field
    public boolean isActive() throws AccountNotActive {
        if (active)
        return active;
        throw new AccountNotActive();
    }

    //setActive method sets active field
    public void setActive(boolean active) {
        this.active = active;
    }

    //getCharges method returens charges field
    public double getCharges() {
        return charges;
    }

    //getCharges method sets charges field
    public void setCharges(double charges) {
        this.charges = charges;
    }

    // debit method drars amount from account
    public boolean debit(double amount) throws NotValidation, AccountNotActive {
        if (isActive() && validate(amount) && this.getCurrentBalance() >= amount) {
            Transaction t = new Transaction();
            t.withdrars(amount);
            this.getTransactList().add(t);
            this.uppdteBalance(amount, TransactionType.DEBIT);
            return true;
        }
        return false;
    }

    // overload debit method drars amount from account with overdraft
    public boolean debit(double amount, double overDraft) throws NotValidation, AccountNotActive {
         if (isActive() && validate(amount) && this.getCurrentBalance() - amount >= overDraft) {
            Transaction t = new Transaction();
            t.withdrars(amount);
            this.getTransactList().add(t);
            this.uppdteBalance(amount, TransactionType.DEBIT);
            return true;
        }
        System.out.println("Can't overdraft more than  1,000 Kr");
        return false;
    }

    // credit method adds amount to account
    public boolean credit(double amount) throws NotValidation, AccountNotActive {
        if (isActive() && validate(amount)) {
            Transaction t = new Transaction();
            t.deposite(amount);
            this.getTransactList().add(t);
            this.uppdteBalance(amount, TransactionType.CREDIT);
            return true;
        }
       return false;
    }

    // validate method trow excaption if amount less than zero.
    private static boolean validate(Double amount) throws NotValidation {
        if (amount < 0.0)
            throw new NotValidation( "Invalid amount $" + amount.toString());
        return true;
    }

    // currentBalance method calcs currentBalance of account from transactions
    public abstract double balance() throws AccountNotActive, NotValidation;

    //uppdteBalance method adds amount or drar amount
    public abstract void uppdteBalance(double amount, TransactionType typeOfTrans) throws AccountNotActive, NotValidation;

    //printTransactionList method prints list of transactions of account
    public void printTransactionList() {
        if(this.getTransactList().size()==0)
            System.out.println("There are not Transactions for this account");
        else this.getTransactList().forEach(System.out::println);
    }

     public boolean remove() {
        if (!this.equals(null)) {
            this.setActive(false);
            return true;
        } else return false;
    }

}
