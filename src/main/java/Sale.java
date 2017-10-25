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
    private int InstallTypeID;

    @DatabaseField
    private String SiteAddress;

    @DatabaseField
    private String SiteSuburb;



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


}
