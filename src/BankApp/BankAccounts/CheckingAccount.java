package BankApp.BankAccounts;

import BankApp.BankTransaction.*;
import BankApp.Exceptions.*;

public class CheckingAccount extends CurrentAccount {
    //Data fields
    private double overDraft=-1000 ;

    //Constructor
    public CheckingAccount(double balance) {
        super(balance);
        this.setType(AccountType.CHECKING);
        this.setCharges(525);

    }

    // getOverDraft method returens overdraft field
    public double getOverDraft() {
        return overDraft;
    }

    // setOverDraft method sets overdraft field
    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    @Override// toString method prints CheckingsAccount
    public String toString() {
        try {
            return "CheckingAccount{" +
                    " AccountNo= " + this.getAccountNo() +
                    ", active=" + this.isActive() +
                    ", openDate=" + this.getOpenDate() +
                    ", charges=" + this.getCharges() +
                    ", Number of bankcard is  " + this.getIdCard() +
                    ", the ending balance is " +this.getCurrentBalance() +
                    ", overDraft=" + overDraft +
                    ", The transaction list is  " + this.getTransactList() +
                    '}';
        } catch (AccountNotActive e) {
            e.getMessage();
        }
        return null;
    }

    @Override// balance method calcs balance from transactions
    public double balance() {
        double sum = 0;
        for (Transaction temp : this.getTransactList()) {
            if (temp.getType().equals(TransactionType.CREDIT))
                sum += temp.getCurrentTransactionMoney();
            else
                sum -= temp.getCurrentTransactionMoney();
        }
        return sum - calcCharges();
    }

    @Override // uppdteBalance method adds amount or drar amount
    public void uppdteBalance(double amount, TransactionType typeOfTrans) throws AccountNotActive, NotValidation {
        if (typeOfTrans.equals(TransactionType.DEBIT)) {
            if (this.getCurrentBalance() - amount>=this.getOverDraft() ) {
                this.setCurrentBalance(this.getCurrentBalance() - amount);
            } else try {
                throw new ExceedOverdraft(" Can't overdraft more than  1,000 Kr");
            } catch (ExceedOverdraft e) {
                System.out.println(e.getMessage());
            }
        } else
            this.setCurrentBalance(this.getCurrentBalance()+amount);    }

}//end of CheckingsAccount class
