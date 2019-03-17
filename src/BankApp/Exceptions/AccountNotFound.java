package BankApp.Exceptions;

public class AccountNotFound extends Throwable {
    public AccountNotFound() {
        super("has not found");
    }
}
