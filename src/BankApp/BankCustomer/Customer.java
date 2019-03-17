package BankApp.BankCustomer;

import BankApp.Exceptions.NotValidation;
import BankApp.Exceptions.uncorrectPassword;

public abstract class Customer {
    // Data fields
    private long personId;
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private CustomerType type;
    private String password;
    private static long count;

    //Chain constractuer
    public Customer(String fname, String lname, String password) {
        count++;
        this.personId = count;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }

    public Customer(String fname, String lname, String password, String address, String phone) {
        this(fname, lname, password);
        this.address = address;
        this.phone = phone;
    }
    //getPersonId method returns person id
    public long getPersonId() {
        return personId;
    }

    //getFname method returns First name
    public String getFname() {
        return fname;
    }

    //setFname method sets First name
    public void setFname(String fname) {
        this.fname = fname;
    }

    //getLname method returns Last name
    public String getLname() {
        return lname;
    }

    //setLname method sets Lname
    public void setLname(String lname) {
        this.lname = lname;
    }

    //getAddress method returns getAddress
    public String getAddress() {
        return address;
    }

    //setAddress method sets Address
    public void setAddress(String address) {
        this.address = address;
    }

    //getPhone method returns Phone
    public String getPhone() {
        return phone;
    }

    //setPhone method sets Phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //getType method returns Customer Type
    public CustomerType getType() {
        return type;
    }

    //setType method sets Type
    public void setType(CustomerType type) {
        this.type = type;
    }

    //getPassword method returns Password
    public String getPassword() {
        return password;
    }

    //setPassword method sets Password
    public void setPassword(String password) {
        this.password = password;
    }

    //changePassword method sets new Password
    public boolean changePassword(String newPass) {
        if (!newPass.equals(null))
            if (!password.equals(newPass)) {
                this.password = newPass;
                return true;
            } else try {
                throw new uncorrectPassword("You must not enter the same password");
            } catch (uncorrectPassword e) {
                System.out.println(e.getMessage());
            }
        else try {
            throw new uncorrectPassword("You must not enter empty password");
        } catch (uncorrectPassword e) {
            System.out.println(e.getMessage());
        }return false;
    }

    //logIn method returns true if password matched or false if did not
    public boolean logIn(String password) {
        if (password.equals(this.password))
            return true;
        else try {
            throw new NotValidation();
        } catch (NotValidation e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //uppdate method sets new data till exist customer
    public void uppdate(String type, String fname, String lname, String address, String phon) {
        this.setAddress(address);
        this.setFname(fname);
        this.setLname(lname);
        this.setPhone(phon);
        this.setType(CustomerType.valueOf(type));
    }

    @Override     //toString method returns String of customers data
    public String toString() {
        return
                "  personId=" + personId +
                        ", fname='" + fname + '\'' +
                        ", lname='" + lname + '\'' +
                        ", address='" + address + '\'' +
                        ", phone='" + phone + '\'' +
                        '}';
    }
}
