package BankApp.myBank;

import BankApp.BankAccounts.*;
import BankApp.BankCustomer.*;

import BankApp.Exceptions.*;

import java.util.Scanner;

public class Handeling {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws NotFound, AccountNotActive {

        MyBank myBank = new MyBank();
        welcom();
        myData(myBank);
        Customer mycust = login(myBank);
        BankAccount myaccount = null;
        boolean continu = true;
        Change: do {
            System.out.print("Please select number from menu \n");
            menu();
            int choice = input.nextInt();
            switch (choice) {
                case 1:    //handling accounts
                    boolean flag = true;
                    while (flag) {
                        try {
                            myaccount = dermineAccount(myBank, mycust);
                        } catch (AccountNotFound e) {
                            System.out.println(e.getMessage());
                            continue;
                        } catch (AccountNotActive e1) {
                            System.out.println(e1.getMessage());
                            continue;
                        } catch (NotFound e2) {
                            System.out.println(e2.getMessage());
                            continue;
                        }
                        System.out.println("Choiced account is :" + myaccount);
                        menu1();
                        boolean continu1 = false;
                        do {
                            int choice1 = input.nextInt();
                            switch (choice1) {
                                case 1:
                                    boolean continu2 = false;
                                    do {
                                        if (!depositMoney(myaccount)) continu2 = true;
                                    } while (continu2);
                                    break;
                                case 2:         //Withdrawn
                                    boolean continu3 = false;
                                    do {
                                        if (!drarMoney(myaccount)) continu3 = true;
                                    } while (continu3);
                                    break;
                                case 3:    //Balance
                                    try {
                                        myaccount.balance();
                                    } catch (NotValidation e) {
                                        System.out.println(e.getMessage());
                                    }
                                    System.out.println("The balance for your account is " + myaccount.getCurrentBalance());
                                    break;
                                case 4:  // Swish
                                    boolean continu4 = false;
                                    do {
                                        if (!Swish(myBank, myaccount)) continu4 = true;
                                        else {
                                            System.out.println("Amount transferred successfully");
                                            continu4 = false;
                                        }
                                    } while (continu4);
                                    break;
                                case 5:   //exit
                                continue Change;
                                default:
                                    System.out.println("Incorect number please select number from menu");
                                    menu1();
                                    continu1 = true;
                            }
                        } while (continu1);
                    }
                    break;
                case 2:    //for repports
                    //myBank.getCustomers().printList();myBank.getAccounts().printAccounts(); myBank.printAll();
                    boolean flag1 = true;
                    while (flag1) {
                        menu2();
                        boolean contin1 = false;
                        do {
                            int choice1 = input.nextInt();
                            switch (choice1) {
                                case 1: // your information
                                    System.out.println(mycust);
                                    break;
                                case 2:         //uppdate information
                                    uppdateProfile(myBank,mycust) ;

                                    break;
                                case 3:    //your accounts
                                    myBank.printAccountsForCustomer(mycust.getPersonId());
                                     break;
                                case 4:  //  your partners
                                    System.out.println("Enter the common account number for print partners");
                                    long commAccountId = input.nextLong();
                                    try {
                                        myBank.printCustomerForAccount(commAccountId);
                                    } catch (NotValidation e) {
                                       System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5: //details of account
                                    System.out.println("Enter the account number for print its Transactions");
                                    long accountId = input.nextLong();
                                    myBank.getAccounts().findAccount(accountId).printTransactionList();
                                    break;
                                case 6:   //exit
                                    continue Change;
                                default:
                                    System.out.println("Incorect number please select number from menu");
                                    menu1();
                                    contin1 = true;
                            }
                        } while (contin1);
                    }
                    break;
                case 3:      //Exit
                    System.out.println("Thank you for dealing with our bank,Welcome Back");
                    System.exit(0);
                    break;
                default:     //for incorrect enter
                    System.out.println("Incorect number please select number from menu");
                    menu();
                    continu = true;
            }
        } while (continu);
    }

    //method welcom prints word bank
    private static void welcom() {
        System.out.print("****************************************************\n");
        System.out.print("*++++++++         +       ++         ++ ++       ++*\n");
        System.out.print("*++      ++      +++      ++++       ++ ++     ++  *\n");
        System.out.print("*++      ++     ++ ++     ++ ++      ++ ++   ++    *\n");
        System.out.print("*++++++++++    +++++++    ++   ++    ++ ++ ++      *\n");
        System.out.print("*++      ++   ++     ++   ++     ++  ++ ++   ++    *\n");
        System.out.print("*++      ++  ++       ++  ++       ++++ ++     ++  *\n");
        System.out.print("*++++++++   ++         ++ ++         ++ ++       ++*\n");
        System.out.print("****************************************************\n");
    }

    //myData methods initialize data of customers and accounts and the link between them
    private static void myData(MyBank myBank) {
        Customer c1 = new Person("Eric", "Svenson", "Eric1", "ksrlskrona", "076512655");
        BankAccount b1 = new CurrentAccount(5000);
        myBank.newCustAcc(c1, b1);
        BankAccount b6 = new DepositAccount(25000);
        myBank.newCustAcc(c1, b6);
        Customer c2 = new Person("Olf", "Erickson", "Olf1", "Malmo", "0765155554");
        BankAccount b2 = new CheckingAccount(5000);
        myBank.newCustAcc(c2, b2);
        BankAccount b3 = new SavingsAccount(15000);
        myBank.newCustAcc(c2, b3);// A common account
        Customer c3 = new Company("Fridrik", "Linderberg", "Fridrik1", "Stockholm", "0765126598");
        myBank.newCustAcc(c3, b3);// A common account
        BankAccount b8 = new CurrentAccount(2500);
        myBank.newCustAcc(c3, b8);
        BankAccount b9 = new CheckingAccount(0);
        myBank.newCustAcc(c3, b9);
        Customer c4 = new Person("Anna", "Carin", "Anna1", "Helmstad", "076512698");
        BankAccount b4 = new CurrentAccount(0);
        myBank.newCustAcc(c4, b4);
        BankAccount b7 = new DepositAccount(100000);
        myBank.newCustAcc(c4, b7);
        Customer c5 = new Person("Olle", "Olson", "Olle1", "ksrlskrona", "076528678");
        BankAccount b5 = new SavingsAccount(55000);
        myBank.newCustAcc(c5, b5);
        BankAccount b10 = new CurrentAccount(500);
        myBank.newCustAcc(c5, b10);

    }

    //login method allow customer to sign in
    private static Customer login(MyBank myBank) {
        long customerId;
        String password;
        Customer customer;
        do {
            System.out.print("Welcom to your bank Accounts please Log in \n");
            System.out.print("Enter your Person Id \n");
            customerId = input.nextLong();
            System.out.print("Enter your password \n");
            input.nextLine();
            password = input.nextLine();
            customer = myBank.getCustomers().findCustomer(customerId);
        } while (!customer.logIn(password));
        System.out.println("Welcome " + customer.getFname());
        return customer;
    }

    // dermineAccount method search for accounts by its id
    private static BankAccount dermineAccount(MyBank myBank, Customer mycust) throws AccountNotFound, AccountNotActive, NotFound {
        System.out.print("Select your Bank Account  \n");
        long accountNo = input.nextLong();
        BankAccount myaccount = myBank.getAccounts().findAccount(accountNo);
        if (myaccount == null) throw new AccountNotFound();
        if (!myaccount.isActive()) throw new AccountNotActive();
        myBank.findMyCustAcc(mycust.getPersonId(), myaccount.getAccountNo());
        return myaccount;
    }

    //methods menu print first menu
    private static void menu() {
        System.out.print(" MENU : \n");
        System.out.print(" 1. Handeling Bank Account\n");
        System.out.print(" 2. Report\n");
        System.out.print(" 3. Exit\n");
    }

    //methods menu1 print sub menu
    private static void menu1() {
        System.out.print("Please select choice from menu: \n");
        System.out.print(" 1. Deposit \n");
        System.out.print(" 2. Withdrawn\n");
        System.out.print(" 3. Balance\n");
        System.out.print(" 4. Swish\n");
        System.out.print(" 5. Exit\n");
    }

    //methods menu2 print sub menu
    private static void menu2() {
        System.out.print("Please select choice from menu: \n");
        System.out.print(" 1. your information \n");
        System.out.print(" 2. update information \n");
        System.out.print(" 3. your accounts \n");
        System.out.print(" 4. your partners\n");
        System.out.print(" 5. details of account\n");
        System.out.print(" 6. Exit\n");
    }

    //methods drarMoney allows to drar money from account
    private static boolean drarMoney(BankAccount myaccount) {
        System.out.println("Enter a positve amount of money to Withdrar");
        double amount = 0;
        try {
            amount = input.nextDouble();
            if (myaccount instanceof CheckingAccount) {
                myaccount.debit(amount, -1000);
            } else myaccount.debit(amount);

            System.out.println("Your balance now is :" + myaccount.getCurrentBalance());
            return true;
        } catch (NotValidation e) {
            System.out.println(e.getMessage());
            return false;
        } catch (AccountNotActive ee) {
            System.out.println(ee.getMessage());
            return false;
        }
    }

    //methods depositMoney allows to deposis money to account
    private static boolean depositMoney(BankAccount myaccount) {
        System.out.println("Enter a positve amount of money to deposite");
        double amount = 0;
        try {
            amount = input.nextDouble();
            myaccount.credit(amount); //Deposit
            System.out.println("Your balance now is :" + myaccount.getCurrentBalance());
            return true;
        } catch (NotValidation e) {
            System.out.println(e.getMessage());
            return false;
        } catch (AccountNotActive ee) {
            System.out.println(ee.getMessage());
            return false;
        }
    }

    //Swish method transfer money from account to another
    private static boolean Swish(MyBank myBank, BankAccount myaccount) throws AccountNotActive {
        System.out.println("Please enter account number that you want send money to it");
        long reciveNo;
        reciveNo = input.nextLong();
        BankAccount reciveAcount = myBank.getAccounts().findAccount(reciveNo);
        System.out.println("Please enter amount of money to swish");
        Double amount = input.nextDouble();
        try {
            myBank.transfer(myaccount, reciveAcount, amount);
            return true;
        } catch (NotValidation e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //uppdateProfile method allow customer to change his data
    private static void uppdateProfile(MyBank myBank, Customer mycustomer)  {
        System.out.println("Please enter your first name");
        String newfName=input.next();
        input.nextLine();
        System.out.println("Please enter your family name");
        String newlName=input.nextLine();
        input.nextLine();
        System.out.println("Please enter your adress");
        String newadress=input.next();
        //input.nextLine();
        System.out.println("Please enter your phone");
        String newphone=input.next();
        input.nextLine();
        System.out.println("You are like "+mycustomer.getType()+" castomer in our bank Please enter your new Type");
        String newTpe =input.next();
        myBank.getCustomers().uppdate(mycustomer.getPersonId(),newTpe,newfName,newlName,newadress,newphone);

    }



}
