/**
 * Created by libbyjennings on 25/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Date;

//Create database table name
@DatabaseTable(tableName = "FIRE")

public class Fire {

    @DatabaseField
    private String FireID;

    @DatabaseField
    private String FireType;

    //Getters and setters
    public Fire() {

    }

    public String getFireID() {
        return FireID;
    }

    public void setFireID(String fireID) {
        FireID = fireID;
    }

    public String getFireType() {
        return FireType;
    }

    public void setFireType(String fireType) {
        FireType = fireType;
    }
}
