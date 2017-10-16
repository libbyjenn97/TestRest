/**
 * Created by libbyjennings on 22/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "CUSTOMER")

public class Customer {

    @DatabaseField
    private int CustomerID;

    @DatabaseField
    private String FirstName;

    @DatabaseField
    private String LastName;

    @DatabaseField
    private String PostalAddress;

    @DatabaseField
    private String PostalSuburb;

    @DatabaseField
    private String PostalCode;

    @DatabaseField
    private String Phone;

    @DatabaseField
    private String Mobile;

    @DatabaseField
    private String Email;

    @DatabaseField
    private String ReesCode;

    //Getters and setters
    public Customer() {

    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPostalAddress() {
        return PostalAddress;
    }

    public void setPostalAddress(String PostalAddress) {
        this.PostalAddress = PostalAddress;
    }

    public String getPostalSuburb() {
        return PostalSuburb;
    }

    public void setPostalSuburb(String PostalSuburb) {
        this.PostalSuburb = PostalSuburb;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getReesCode() {
        return ReesCode;
    }

    public void setReesCode(String ReesCode) {
        this.ReesCode = ReesCode;
    }
}

