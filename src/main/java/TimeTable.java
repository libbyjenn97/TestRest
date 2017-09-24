/**
 * Created by libbyjennings on 23/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "TIMETABLE")

public class TimeTable {

    @DatabaseField
    private String TimeID;

    public String getTimeID() {
        return TimeID;
    }

    public void setTimeID(String timeID) {
        TimeID = timeID;
    }
}
