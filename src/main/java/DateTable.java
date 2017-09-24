/**
 * Created by libbyjennings on 23/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Date;

//Create database table name
@DatabaseTable(tableName = "DATETABLE")

public class DateTable {
    @DatabaseField
    private Date DateValue;

    //Getters and setters
    public DateTable() {

    }

    public Date getDateValue() {
        return DateValue;
    }

    public void setDateValue(Date dateValue) {
        DateValue = dateValue;
    }
}
