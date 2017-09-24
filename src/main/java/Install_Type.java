/**
 * Created by libbyjennings on 23/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "INSTALL_TYPE")

public class Install_Type {

    @DatabaseField
    private int InstallTypeID;

    @DatabaseField
    private String InstallDescription;

    @DatabaseField
    private int BasePrice;

    @DatabaseField
    private String EmailFromLetter;

    @DatabaseField
    private String SiteCheckFile;

    public Install_Type() {

    }

    public int getInstallTypeID() {
        return InstallTypeID;
    }

    public void setInstallTypeID(int installTypeID) {
        InstallTypeID = installTypeID;
    }

    public String getInstallDescription() {
        return InstallDescription;
    }

    public void setInstallDescription(String installDescription) {
        InstallDescription = installDescription;
    }

    public int getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(int basePrice) {
        BasePrice = basePrice;
    }

    public String getEmailFromLetter() {
        return EmailFromLetter;
    }

    public void setEmailFromLetter(String emailFromLetter) {
        EmailFromLetter = emailFromLetter;
    }

    public String getSiteCheckFile() {
        return SiteCheckFile;
    }

    public void setSiteCheckFile(String siteCheckFile) {
        SiteCheckFile = siteCheckFile;
    }
}
