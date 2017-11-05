import com.j256.ormlite.field.DatabaseField;

import java.sql.Date;

/*
 * Created by Libby Jennings on 25/10/17.
 * Description: Class for getting booking details
 */
class Booking {

    @DatabaseField
    private Date InstallDate;

    @DatabaseField
    private String InstallTime;

    @DatabaseField
    private int UserID;

    @DatabaseField
    private int InstallID;

    @DatabaseField
    private int SaleID;

    @DatabaseField
    private String FireID;

    @DatabaseField
    private String StockList;


    @DatabaseField
    private String InstallerID;

    @DatabaseField
    private String NoteToInstaller;

    @DatabaseField
    private boolean InstallComplete;

    @DatabaseField
    private String InstallerNote;

    @DatabaseField
    private int CustomerID;

    @DatabaseField
    private int InstallTypeID;

    @DatabaseField
    private String SiteAddress;

    @DatabaseField
    private String SiteSuburb;

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
    private String FireType;

    @DatabaseField
    private String InstallDescription;

    Date getInstallDate() {
        return InstallDate;
    }

    void setInstallDate(Date installDate) {
        InstallDate = installDate;
    }

    String getInstallTime() {
        return InstallTime;
    }

    void setInstallTime(String installTime) {
        InstallTime = installTime;
    }

    int getUserID() {
        return UserID;
    }

    void setUserID(int userID) {
        UserID = userID;
    }

    int getInstallID() {
        return InstallID;
    }

    void setInstallID(int installID) {
        InstallID = installID;
    }

    int getSaleID() {
        return SaleID;
    }

    void setSaleID(int saleID) {
        SaleID = saleID;
    }

    String getFireID() {
        return FireID;
    }

    void setFireID(String fireID) {
        FireID = fireID;
    }

    String getStockList() {
        return StockList;
    }

    void setStockList(String stockList) {
        StockList = stockList;
    }

    String getInstallerID() {
        return InstallerID;
    }

    void setInstallerID(String installerID) {
        InstallerID = installerID;
    }

    String getNoteToInstaller() {
        return NoteToInstaller;
    }

    void setNoteToInstaller(String noteToInstaller) {
        NoteToInstaller = noteToInstaller;
    }

    boolean isInstallComplete() {
        return InstallComplete;
    }

    void setInstallComplete(boolean installComplete) {
        InstallComplete = installComplete;
    }

    String getInstallerNote() {
        return InstallerNote;
    }

    void setInstallerNote(String installerNote) {
        InstallerNote = installerNote;
    }

    int getCustomerID() {
        return CustomerID;
    }

    void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    int getInstallTypeID() {
        return InstallTypeID;
    }

    void setInstallTypeID(int installTypeID) {
        InstallTypeID = installTypeID;
    }

    String getSiteAddress() {
        return SiteAddress;
    }

    void setSiteAddress(String siteAddress) {
        SiteAddress = siteAddress;
    }

    String getSiteSuburb() {
        return SiteSuburb;
    }

    void setSiteSuburb(String siteSuburb) {
        SiteSuburb = siteSuburb;
    }

    String getFirstName() {
        return FirstName;
    }

    void setFirstName(String firstName) {
        FirstName = firstName;
    }

    String getLastName() {
        return LastName;
    }

    void setLastName(String lastName) {
        LastName = lastName;
    }

    String getPostalAddress() {
        return PostalAddress;
    }

    void setPostalAddress(String postalAddress) {
        PostalAddress = postalAddress;
    }

    String getPostalSuburb() {
        return PostalSuburb;
    }

    void setPostalSuburb(String postalSuburb) {
        PostalSuburb = postalSuburb;
    }

    String getPostalCode() {
        return PostalCode;
    }

    void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    String getPhone() {
        return Phone;
    }

    void setPhone(String phone) {
        Phone = phone;
    }

    String getMobile() {
        return Mobile;
    }

    void setMobile(String mobile) {
        Mobile = mobile;
    }

    String getEmail() {
        return Email;
    }

    void setEmail(String email) {
        Email = email;
    }

    String getFireType() {
        return FireType;
    }

    void setFireType(String fireType) {
        FireType = fireType;
    }

    String getInstallDescription() {
        return InstallDescription;
    }

    void setInstallDescription(String installDescription) {
        InstallDescription = installDescription;
    }
}
