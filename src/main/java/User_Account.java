/**
 * Created by libbyjennings on 23/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "USER_ACCOUNT")

public class User_Account {

    @DatabaseField
    private int UserID;

    @DatabaseField
    private int AuthenticationID;

    @DatabaseField
    private String UserName;

    @DatabaseField
    private String NZHHA_Number;

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
    private int ReesNumber;

    @DatabaseField
    private boolean AccountActive;

    //Getters and setters
    public User_Account() {

    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAuthenticationID() {
        return AuthenticationID;
    }

    public void setAuthenticationID(int authenticationID) {
        AuthenticationID = authenticationID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNZHHA_Number() {
        return NZHHA_Number;
    }

    public void setNZHHA_Number(String NZHHA_Number) {
        this.NZHHA_Number = NZHHA_Number;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPostalAddress() {
        return PostalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        PostalAddress = postalAddress;
    }

    public String getPostalSuburb() {
        return PostalSuburb;
    }

    public void setPostalSuburb(String postalSuburb) {
        PostalSuburb = postalSuburb;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getReesNumber() {
        return ReesNumber;
    }

    public void setReesNumber(int reesNumber) {
        ReesNumber = reesNumber;
    }

    public boolean isAccountActive() {
        return AccountActive;
    }

    public void setAccountActive(boolean accountActive) {
        AccountActive = accountActive;
    }
}
