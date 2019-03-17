package BankApp.BankTransaction;

import java.time.LocalDate;

public class Transaction {
    //Data field
    private TransactionType type;
    private double currentTransactionMoney;
    private LocalDate date;

    //getType method returens type field
    public TransactionType getType() {
        return type;
    }

    //getCurrentTransactionMoney method returens currentTransactionMoney field
    public double getCurrentTransactionMoney() {
        return currentTransactionMoney;
    }

    //setCurrentTransactionMoney method sets currentTransactionMoney field
    public void setCurrentTransactionMoney(int currentTransactionMoney) {
        this.currentTransactionMoney = currentTransactionMoney;
    }

    //getDate method returens date field
    public LocalDate getDate() {
        return date;
    }

    //setDate method sets date field
    public void setDate(LocalDate date) {
        this.date = date;
    }

    //credit method sets type of transaction as credit and amount of money and date
    public void deposite(double amount) {
        this.type = TransactionType.CREDIT;
        this.currentTransactionMoney = amount;
        this.date = LocalDate.now();
    }

    //credit method sets type of transaction as withdrar and amount of money and date
    public void withdrars(double amount) {
        this.type = TransactionType.DEBIT;
        this.currentTransactionMoney = amount;
        this.date = LocalDate.now();
    }

    @Override // toString method to print transaction as deposit or withdraw
    public String toString() {
        if (this.type.equals(TransactionType.CREDIT))
            return "Transaction deposit, with amount " + currentTransactionMoney + ", date=" + date + "\n";
        else
            return "Transaction withdraw, with amount " + currentTransactionMoney + ", date=" + date + "\n";
    }


}
