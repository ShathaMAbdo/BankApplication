package BankApp.BankAccounts;

import BankApp.BankTransaction.Transaction;
import BankApp.Exceptions.*;
import java.time.*;

public class DepositAccount extends SavingsAccount {
    //Data field
    private static final Period depositP=Period.ofMonths(6);

    //Constructer
    public DepositAccount(double Balance) {
        super(Balance);
        this.setAnnualInterestRate(5.0);
        this.setType(AccountType.DEPOSIT);
    }

    @Override//withdrar override method with new condition about period
    public boolean debit(double amount) throws AccountNotActive, NotValidation {
        Period currentP=Period.between(this.getOpenDate(),LocalDate.now());
        if (isActive() && balance() >= amount && currentP.getMonths()>=depositP.getMonths()) {
            Transaction t = new Transaction();
            t.withdrars(amount);
            this.getTransactList().add(t);
            return true;
        }
        return false;
    }

}
