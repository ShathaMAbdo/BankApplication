package BankApp.Exceptions;

public class NotValidation extends Throwable {
    public NotValidation(){
        super("The password that you enter incorrect");
    }
    public NotValidation(String s){
        super(s);
    }
}
