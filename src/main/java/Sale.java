/**
 * Created by libbyjennings on 25/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

//Create database table name
@DatabaseTable(tableName = "SALE")

public class Sale {

    @DatabaseField
    private int SaleID;

    @DatabaseField
    private int CustomerID;

    @DatabaseField
    private int UserID;

    @DatabaseField
    private int InstallTypeID;

    @DatabaseField
    private String SiteAddress;

    @DatabaseField
    private String SiteSuburb;

    @DatabaseField
    private String SaleStatus;

    @DatabaseField
    private String Fire;

    @DatabaseField
    private int Price;

    @DatabaseField
    private boolean SiteCheckBooked;

    @DatabaseField
    private Date SiteCheckDate;


    @DatabaseField
    private String SiteCheckTime;

    @DatabaseField
    private int SiteCheckBy;

    @DatabaseField
    private int SalesPerson;

    @DatabaseField
    private Date EstimationDate;

    @DatabaseField
    private String QuoteNumber;

    @DatabaseField
    private String SiteCheckPath;

    @DatabaseField
    private String QuotePath;

    @DatabaseField
    private String PhotoPath;

    @DatabaseField
    private Date FollowUpDate;

    //Getters and setters
    public Sale() {

    }

    public int getSaleID() {
        return SaleID;
    }

    public void setSaleID(int saleID) {
        SaleID = saleID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getInstallTypeID() {
        return InstallTypeID;
    }

    public void setInstallTypeID(int installTypeID) {
        InstallTypeID = installTypeID;
    }

    public String getSiteAddress() {
        return SiteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        SiteAddress = siteAddress;
    }

    public String getSiteSuburb() {
        return SiteSuburb;
    }

    public void setSiteSuburb(String siteSuburb) {
        SiteSuburb = siteSuburb;
    }

    public String getSaleStatus() {
        return SaleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        SaleStatus = saleStatus;
    }

    public String getFire() {
        return Fire;
    }

    public void setFire(String fire) {
        Fire = fire;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public boolean isSiteCheckBooked() {
        return SiteCheckBooked;
    }

    public void setSiteCheckBooked(boolean siteCheckBooked) {
        SiteCheckBooked = siteCheckBooked;
    }

    public Date getSiteCheckDate() {
        return SiteCheckDate;
    }

    public void setSiteCheckDate(Date siteCheckDate) {
        SiteCheckDate = siteCheckDate;
    }

    public String getSiteCheckTime() {
        return SiteCheckTime;
    }

    public void setSiteCheckTime(String siteCheckTime) {
        SiteCheckTime = siteCheckTime;
    }

    public int getSiteCheckBy() {
        return SiteCheckBy;
    }

    public void setSiteCheckBy(int siteCheckBy) {
        SiteCheckBy = siteCheckBy;
    }

    public int getSalesPerson() {
        return SalesPerson;
    }

    public void setSalesPerson(int salesPerson) {
        SalesPerson = salesPerson;
    }

    public Date getEstimationDate() {
        return EstimationDate;
    }

    public void setEstimationDate(Date estimationDate) {
        EstimationDate = estimationDate;
    }

    public String getQuoteNumber() {
        return QuoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        QuoteNumber = quoteNumber;
    }

    public String getSiteCheckPath() {
        return SiteCheckPath;
    }

    public void setSiteCheckPath(String siteCheckPath) {
        SiteCheckPath = siteCheckPath;
    }

    public String getQuotePath() {
        return QuotePath;
    }

    public void setQuotePath(String quotePath) {
        QuotePath = quotePath;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public Date getFollowUpDate() {
        return FollowUpDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        FollowUpDate = followUpDate;
    }

}
