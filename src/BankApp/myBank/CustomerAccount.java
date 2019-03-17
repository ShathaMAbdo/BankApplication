package BankApp.myBank;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerAccount {
    //Data field
    private int Fiscal_year;
    private Long customerId;
    private Long accountId;

    //constructor
    public CustomerAccount(Long customerId, Long accountId) {
        Fiscal_year = LocalDate.now().getYear();
        this.customerId = customerId;
        this.accountId = accountId;
    }

    //getFiscal_year method returns Fiscal year for calc renta and tax
    public int getFiscal_year() {
        return Fiscal_year;
    }

    //setFiscal_year method sets Fiscal year
    public void setFiscal_year(int fiscal_year) {
        Fiscal_year = fiscal_year;
    }

    //getCustomerId mthods returns customer Id
    public Long getCustomerId() {
        return customerId;
    }

    //setCustomerId mthods sets customer Id
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    //getAccountId methods returns account id
    public Long getAccountId() {
        return accountId;
    }

    //setAccountId mthods sets account Id
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override //equals mthods for compare my object with another
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccount)) return false;
        CustomerAccount that = (CustomerAccount) o;
        return getFiscal_year() == that.getFiscal_year() &&
                Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getAccountId(), that.getAccountId());
    }

    @Override//hashCode method
    public int hashCode() {
        return Objects.hash(getFiscal_year(), getCustomerId(), getAccountId());
    }
}
