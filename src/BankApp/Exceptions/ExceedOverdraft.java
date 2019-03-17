package BankApp.Exceptions;

public class ExceedOverdraft extends Throwable {
    public ExceedOverdraft(String s) {
        super(s);
    }
}
