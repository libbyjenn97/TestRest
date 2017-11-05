/*
 * Created by Libby Jennings on 22/09/17.
 * Description: Class for getting customer details
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "CUSTOMER")
class Customer {

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

    int getCustomerID() {
        return CustomerID;
    }

    void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    String getFirstName() {
        return FirstName;
    }

    void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    String getLastName() {
        return LastName;
    }

    void setLastName(String LastName) {
        this.LastName = LastName;
    }

    String getPostalAddress() {
        return PostalAddress;
    }

    void setPostalAddress(String PostalAddress) {
        this.PostalAddress = PostalAddress;
    }

    String getPostalSuburb() {
        return PostalSuburb;
    }

    void setPostalSuburb(String PostalSuburb) {
        this.PostalSuburb = PostalSuburb;
    }

    String getPostalCode() {
        return PostalCode;
    }

    void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    String getPhone() {
        return Phone;
    }

    void setPhone(String Phone) {
        this.Phone = Phone;
    }

    String getMobile() {
        return Mobile;
    }

    void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    String getEmail() {
        return Email;
    }

    void setEmail(String Email) {
        this.Email = Email;
    }
}

