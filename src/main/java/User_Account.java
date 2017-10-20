/**
 * Created by libbyjennings on 23/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "USER_ACCOUNT")

public class User_Account {

    @DatabaseField
    private int UserID;

    @DatabaseField
    private int AuthenticationID;

    @DatabaseField
    private String UserName;

    @DatabaseField
    private String RoleType;

    @DatabaseField
    private boolean Install;

    @DatabaseField
    private boolean AccountActive;

    @DatabaseField
    private String PasswordHash;

    //Getters and setters
    public User_Account() {

    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAuthenticationID() {
        return AuthenticationID;
    }

    public void setAuthenticationID(int authenticationID) {
        AuthenticationID = authenticationID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public boolean isAccountActive() {
        return AccountActive;
    }

    public void setAccountActive(boolean accountActive) {
        AccountActive = accountActive;
    }

    public String getRoleType() {
        return RoleType;
    }

    public void setRoleType(String roleType) {
        RoleType = roleType;
    }

    public boolean isInstall() {
        return Install;
    }

    public void setInstall(boolean install) {
        Install = install;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }
}
