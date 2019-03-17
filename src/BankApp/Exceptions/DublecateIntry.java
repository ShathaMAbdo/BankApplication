package BankApp.Exceptions;

public class DublecateIntry extends Exception {
    public DublecateIntry(){
        super("This item already exists ");
    }
}
