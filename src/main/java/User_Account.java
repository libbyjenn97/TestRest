/*
 * Created by Libby Jennings on 23/09/17.
 * Description: Class for getting user account details
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "USER_ACCOUNT")
class User_Account {

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

    int getUserID() {
        return UserID;
    }

    void setUserID(int userID) {
        UserID = userID;
    }

    int getAuthenticationID() {
        return AuthenticationID;
    }

    void setAuthenticationID(int authenticationID) {
        AuthenticationID = authenticationID;
    }

    String getUserName() {
        return UserName;
    }

    void setUserName(String userName) {
        UserName = userName;
    }

    boolean isAccountActive() {
        return AccountActive;
    }

    void setAccountActive(boolean accountActive) {
        AccountActive = accountActive;
    }

    String getRoleType() {
        return RoleType;
    }

    void setRoleType(String roleType) {
        RoleType = roleType;
    }

    boolean isInstall() {
        return Install;
    }

    void setInstall(boolean install) {
        Install = install;
    }

    String getPasswordHash() {
        return PasswordHash;
    }

    void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }
}
