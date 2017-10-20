/**
 * Created by libbyjennings on 25/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Date;

//Create database table name
@DatabaseTable(tableName = "INSTALL")

public class Install {

    @DatabaseField
    private int InstallID;

    @DatabaseField
    private int SaleID;

    @DatabaseField
    private String FireID;

    @DatabaseField
    private String StockList;

    @DatabaseField
    private String InstallStatus;

    @DatabaseField
    private boolean OrderChecked;

    @DatabaseField
    private String InstallerID;

    @DatabaseField
    private Date InstallDate;

    @DatabaseField
    private String InstallTime;

    @DatabaseField
    private int PartsReady;

    @DatabaseField
    private String NoteToInstaller;

    @DatabaseField
    private boolean InstallComplete;

    @DatabaseField
    private String InstallerNote;

    //Getters and setters
    public Install() {

    }

    public int getInstallID() {
        return InstallID;
    }

    public void setInstallID(int installID) {
        InstallID = installID;
    }

    public int getSaleID() {
        return SaleID;
    }

    public void setSaleID(int saleID) {
        SaleID = saleID;
    }

    public String getInstallStatus() {
        return InstallStatus;
    }

    public void setInstallStatus(String installStatus) {
        InstallStatus = installStatus;
    }

    public boolean isOrderChecked() {
        return OrderChecked;
    }

    public void setOrderChecked(boolean orderChecked) {
        OrderChecked = orderChecked;
    }

    public String getInstallerID() {
        return InstallerID;
    }

    public void setInstallerID(String installerID) {
        InstallerID = installerID;
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

    public int getPartsReady() {
        return PartsReady;
    }

    public void setPartsReady(int partsReady) {
        PartsReady = partsReady;
    }

    public String getNoteToInstaller() {
        return NoteToInstaller;
    }

    public void setNoteToInstaller(String noteToInstaller) {
        NoteToInstaller = noteToInstaller;
    }

    public boolean isInstallComplete() {
        return InstallComplete;
    }

    public void setInstallComplete(boolean installComplete) {
        InstallComplete = installComplete;
    }

    public String getInstallerNote() {
        return InstallerNote;
    }

    public void setInstallerNote(String installerNote) {
        InstallerNote = installerNote;
    }
    public String getFireID() {
        return FireID;
    }

    public String getStockList() {
        return StockList;
    }

    public void setStockList(String stockList) {
        StockList = stockList;
    }

    public void setFireID(String fireID) {
        FireID = fireID;

    }
}
