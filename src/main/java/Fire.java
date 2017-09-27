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

    @DatabaseField
    private String Make;

    @DatabaseField
    private String Model;

    @DatabaseField
    private String Fuel;

    @DatabaseField
    private String ECAN;

    @DatabaseField
    private String Nelson;

    @DatabaseField
    private String Life;

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

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getFuel() {
        return Fuel;
    }

    public void setFuel(String fuel) {
        Fuel = fuel;
    }

    public String getECAN() {
        return ECAN;
    }

    public void setECAN(String ECAN) {
        this.ECAN = ECAN;
    }

    public String getNelson() {
        return Nelson;
    }

    public void setNelson(String nelson) {
        Nelson = nelson;
    }

    public String getLife() {
        return Life;
    }

    public void setLife(String life) {
        Life = life;
    }
}
