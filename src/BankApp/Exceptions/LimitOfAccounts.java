package BankApp.Exceptions;

public class LimitOfAccounts extends Exception {

    public LimitOfAccounts(String s){
        super(s+" can not have more than 10 accounts");
    }
}
