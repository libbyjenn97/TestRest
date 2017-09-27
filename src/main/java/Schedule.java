/**
 * Created by libbyjennings on 25/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Date;

//Create database table name
@DatabaseTable(tableName = "SCHEDULE")

public class Schedule {

    @DatabaseField
    private Date InstallDate;

    @DatabaseField
    private String InstallTime;

    @DatabaseField
    private int UserID;

    @DatabaseField
    private int InstallID;

    //Getters and setters
    public Schedule() {

    }

    public Date getInstallDate() {
        return InstallDate;
    }

    public void setInstallDate(Date installDate) {
        InstallDate = installDate;
    }

    public String getInstallTime() {
        return InstallTime;
    }

    public void setInstallTime(String installTime) {
        InstallTime = installTime;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getInstallID() {
        return InstallID;
    }

    public void setInstallID(int installID) {
        InstallID = installID;
    }
}
