package BankApp.Exceptions;

public class AccountNotActive extends Throwable {
    public AccountNotActive() {
        super(" This account is not active");
    }
}
