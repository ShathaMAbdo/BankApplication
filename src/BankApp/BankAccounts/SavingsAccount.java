package BankApp.BankAccounts;

import BankApp.BankTransaction.*;
import BankApp.Exceptions.*;
import java.time.*;

public class SavingsAccount extends BankAccount {
    //Data fields
    private double annualInterestRate = 2.5;
    private double lastAmountEarned;
    private static final double startBalance = 10000;

    //Constructor
    public SavingsAccount(double Balance) {
        super(Balance);
        this.setAccountNo(++count);
        try {
            if (this.getCurrentBalance() < startBalance) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("You cant open Saving accounts with balance less than " + startBalance);
            this.setType(AccountType.SAVING);
        }
    }

    // setAnnualInterestRate method sets annualInterestRate field
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    // getAnnualInterestRate method returens annualInterestRate field
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    //method addMonthlyInterest calcs lastAmountEarned
    public void addMonthlyInterest(double bal) {
        lastAmountEarned = bal * annualInterestRate / 12;
    }

    // calcInterest method calcs earned Amount for defined month
    private void calcInterest(int month) throws AccountNotActive, NotValidation {
        double totalWithdraws = 0, totalDeposits = 0;
        LocalDate OpenpDate = this.getOpenDate();
        for (Transaction temp : this.getTransactList()) {
            if (temp.getDate().compareTo(OpenpDate.plusMonths(month)) > 0 &&
                    temp.getDate().compareTo(OpenpDate.plusMonths(month + 1)) <= 0)
                if (temp.getType().equals(TransactionType.DEBIT))
                    totalWithdraws += temp.getCurrentTransactionMoney();
                else
                    totalDeposits += temp.getCurrentTransactionMoney();
        }
        if ((totalDeposits - totalWithdraws) > 0) {
            this.addMonthlyInterest(totalDeposits - totalDeposits);
            uppdteBalance(lastAmountEarned, TransactionType.CREDIT);
        }
    }

    @Override // toString method prints SavingAccount
    public String toString() { // toString method displays the ending details of the savings account
        try {
            return "SavingsAccount{" +
                    " AccountNo= " + this.getAccountNo() +
                    ", active=" + this.isActive() +
                    ", openDate=" + this.getOpenDate() +
                    ", The balance before adding interest is: $" + Math.round(this.getCurrentBalance() * 100) / 100.0 +
                    ", annualInterestRate=" + annualInterestRate +
                    ", monthlyInterestRate=" + annualInterestRate / 12.0 +
                    ", Total interest earned: $" + Math.round(lastAmountEarned * 100) / 100.0 +
                    ", The ending balance is: $" + Math.round(this.balance() * 100) / 100.0 +
                    '}';
        } catch (AccountNotActive e) {
            e.getMessage();
        } catch (NotValidation e) {
            e.getMessage();
        }
        return null;
    }//end of toString method

    @Override// balance method calcs balance from transactions with interest
    public double balance() throws AccountNotActive, NotValidation {
        int month = Period.between(LocalDate.now(), this.getOpenDate()).getMonths();
        for (int i = 0; i < month; i++) {
            calcInterest(i);
        }
        return this.getCurrentBalance();
    }//end of balance method

    @Override // uppdteBalance method adds amount or drar amount

    public void uppdteBalance(double amount, TransactionType typeOfTrans) throws AccountNotActive, NotValidation{
        if (typeOfTrans.equals(TransactionType.CREDIT))
            this.setCurrentBalance(this.getCurrentBalance()+amount);
        else
            this.setCurrentBalance(this.getCurrentBalance()-amount);
    }//end of uppdteBalance method
}
//end of SavingsAccount class