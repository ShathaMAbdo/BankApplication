package BankApp.BankAccounts;

import BankApp.BankTransaction.*;
import BankApp.Exceptions.*;
import java.time.*;

public class CurrentAccount extends BankAccount {
    //Data fields
    private long idCard;

    //Constructor
      public CurrentAccount(double balance) {
        super(balance);
        this.setType(AccountType.CURRENT);
        this.setCharges(500);
        this.setAccountNo(++count);
    }

    public CurrentAccount(double balance, long idCard) {
        this(balance);
        this.idCard = idCard;
    }
    //getIdCard method returens idcard field
    public long getIdCard() {
        return idCard;
    }

    //setIdCard method sets idcard field
    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    //debit method with idcard
    public boolean withdrars(double amount, long idCard) throws AccountNotActive, NotValidation {
        if (this.idCard == idCard) {
            super.debit(amount);
            return true;
        }
        return false;
    }

    //depodite method with idcard
    public boolean deposite(double amount, long idCard) throws AccountNotActive, NotValidation {
        if (this.idCard == idCard) {
            super.credit(amount);
            return true;
        }
        return false;
    }

    //calcCharges method calcs totalcharges for defined charges
    public double calcCharges() {
        LocalDate currentDate = LocalDate.now();
        int month = Period.between(this.getOpenDate(),LocalDate.now()).getMonths();
        double totalcharges=0;
        for (int i = 1; i <=month ; i++) {
            totalcharges+=this.getCharges()/12;
        }
        return totalcharges;
    }

    @Override // toString method prints CurrentAccount
    public String toString() {
        try {
            return "CurrentAccount{" +
                    " AccountNo= " + this.getAccountNo() +
                    ", active=" + this.isActive() +
                    ", openDate=" + this.getOpenDate() +
                    ", charges= " + this.getCharges() +
                    ", The balance = " + this.getCurrentBalance() +
                    ", Number of bankcard =  " + idCard +
                    ", The transaction list is  " + this.getTransactList() +
                    '}';
        } catch (AccountNotActive e) {
            e.getMessage();
        }
        return null;
    }

    @Override // balance method calcs balance from transactions
    public double balance() {
        double sum = 0;
        for (Transaction temp : this.getTransactList()) {
            if (temp.getType().equals(TransactionType.CREDIT))
                sum += temp.getCurrentTransactionMoney();
            else
                sum -= temp.getCurrentTransactionMoney();
        }
        return sum-calcCharges();
    }

    @Override // uppdteBalance method adds amount or drar amount
    public void uppdteBalance(double amount, TransactionType typeOfTrans) throws AccountNotActive, NotValidation {
        if (typeOfTrans.equals(TransactionType.CREDIT))
            this.setCurrentBalance(this.getCurrentBalance()+amount);
        else
            this.setCurrentBalance(this.getCurrentBalance()-amount);
    }

}//end of CurrentAccount class
