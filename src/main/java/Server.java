/**
 * Created by Libby Jennings on 6/01/16.
 */


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import spark.Request;
import spark.Response;
import org.json.*;
import java.sql.SQLException;
import java.util.List;
import static spark.Spark.*;

public class Server {


    public static void main(String[] args) throws SQLException {

        //Set port number
        port(1997);
        //Set database url
        String databaseUrl = "jdbc:sqlserver://wchdb.cnfoxyxq90wv.ap-southeast-2.rds.amazonaws.com:1433;databaseName=AWS_WCH_DB";
        //Connect to database, set username and password
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        ((JdbcConnectionSource) connectionSource).setUsername("Khgv92367hdkfug9");
        ((JdbcConnectionSource) connectionSource).setPassword("Locei02h84b5KJUVaW");

        //create DAO
        Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
        //TableUtils.createTableIfNotExists(connectionSource, Plant.class);

        //Post request sends customer details to the app
        post("/addcustomer", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            int CustomerID = obj.getInt("CustomerID");
            String FirstName = obj.getString("FirstName");
            String LastName = obj.getString("LastName");
            String PostalAddress = obj.getString("PostalAddress");
            String PostalSuburb = obj.getString("PostalSuburb");
            String PostalCode = obj.getString("PostalCode");
            String Phone = obj.getString("Phone");
            String Mobile = obj.getString("Mobile");
            String Email = obj.getString("Email");
            String ReesCode = obj.getString("ReesCode");

            Customer customer = new Customer();
            customer.setCustomerID(CustomerID);
            customer.setFirstName(FirstName);
            customer.setLastName(LastName);
            customer.setPostalAddress(PostalAddress);
            customer.setPostalSuburb(PostalSuburb);
            customer.setPostalCode(PostalCode);
            customer.setPhone(Phone);
            customer.setMobile(Mobile);
            customer.setEmail(Email);
            customer.setReesCode(ReesCode);

            customerDao.create(customer);
            return "OK";

        });

        //Get request for getting customer details from the app
        get("/getcustomers", (request, response)-> {

            //Create an array with customer details
            List<Customer> customers = customerDao.queryForAll();
            JSONArray customerArray = new JSONArray();
            for (Customer customer : customers) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("CustomerID", customer.getCustomerID());
                    obj.put("FirstName", customer.getFirstName());
                    obj.put("LastName", customer.getLastName());
                    obj.put("PostalAddress", customer.getPostalAddress());
                    obj.put("PostalSuburb", customer.getPostalSuburb());
                    obj.put("PostalCode", customer.getPostalCode());
                    obj.put("Phone", customer.getPhone());
                    obj.put("Mobile", customer.getMobile());
                    obj.put("Email", customer.getEmail());
                    obj.put("ReesCode", customer.getReesCode());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                customerArray.put(obj);
            }
            return customerArray.toString();
        });

        //create DAO
        Dao<DateTable, String> dateTableDao = DaoManager.createDao(connectionSource, DateTable.class);

        //Post request sends date table details to the app
        /*post("/adddate", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            String DateValueString = obj.getString("CustomerID");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date DateValueUtil = sdf1.parse(DateValueString);
            java.sql.Date DateValueSql = new java.sql.Date(DateValueUtil.getTime());

            DateTable dateTable = new DateTable();
            dateTable.setDateValue(DateValueSql);

            dateTableDao.create(dateTable);
            return "OK";

        });*/

        //Get request for getting date table details from the app
        get("/getdatetable", (request, response)-> {

            //Create an array with dates
            List<DateTable> dateTables = dateTableDao.queryForAll();
            JSONArray dateTableArray = new JSONArray();
            for (DateTable dateTable : dateTables) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("DateValue", dateTable.getDateValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dateTableArray.put(obj);
            }
            return dateTableArray.toString();
        });

        //create DAO
        Dao<TimeTable, String> timeTableStringDao = DaoManager.createDao(connectionSource, TimeTable.class);

        //Get request for getting date table details from the app
        get("/gettimetable", (request, response)-> {

            //Create an array with dates
            List<TimeTable> timeTables = timeTableStringDao.queryForAll();
            JSONArray timeTableArray = new JSONArray();
            for (TimeTable timeTable : timeTables) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("TimeID", timeTable.getTimeID());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                timeTableArray.put(obj);
            }
            return timeTableArray.toString();
        });

        //create DAO
        Dao<Authenticate, String> authenticateStringDao = DaoManager.createDao(connectionSource, Authenticate.class);

        //Post request sends date table details to the app
        post("/addauthentication", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            int AuthenicationID = obj.getInt("AuthenticationID");
            String PasswordHash = obj.getString("PasswordHash");

            Authenticate authenticate = new Authenticate();
            authenticate.setAuthenticationID(AuthenicationID);
            authenticate.setPasswordHash(PasswordHash);

            authenticateStringDao.create(authenticate);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getauthentication", (request, response)-> {

            //Create an array with dates
            List<Authenticate> authenticateList = authenticateStringDao.queryForAll();
            JSONArray authenticateArray = new JSONArray();
            for (Authenticate authenticate : authenticateList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("AuthenticationID", authenticate.getAuthenticationID());
                    obj.put("PasswordHash", authenticate.getPasswordHash());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                authenticateArray.put(obj);
            }
            return authenticateArray.toString();
        });

        //create DAO
        Dao<User_Account, String> user_accountStringDao = DaoManager.createDao(connectionSource, User_Account.class);

        //Post request sends date table details to the app
        post("/adduseraccount", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            int UserID = obj.getInt("UserID");
            int AuthenticationID = obj.getInt("AuthenticationID");
            String UserName = obj.getString("UserName");
            String Role = obj.getString("Role");
            String NZHHA_Number = obj.getString("NZHHA_Number");
            String FirstName = obj.getString("FirstName");
            String LastName = obj.getString("LastName");
            String PostalAddress = obj.getString("PostalAddress");
            String PostalSuburb = obj.getString("PostalSuburb");
            String PostalCode = obj.getString("PostalCode");
            String Phone = obj.getString("Phone");
            String Mobile = obj.getString("Mobile");
            String Email = obj.getString("Email");
            int ReesNumber = obj.getInt("ReesNumber");
            boolean AccountActive = obj.getBoolean("AccountActive");

            User_Account user_account = new User_Account();
            user_account.setUserID(UserID);
            user_account.setAuthenticationID(AuthenticationID);
            user_account.setUserName(UserName);
            user_account.setRole(Role);
            user_account.setNZHHA_Number(NZHHA_Number);
            user_account.setFirstName(FirstName);
            user_account.setLastName(LastName);
            user_account.setPostalAddress(PostalAddress);
            user_account.setPostalSuburb(PostalSuburb);
            user_account.setPostalCode(PostalCode);
            user_account.setPhone(Phone);
            user_account.setMobile(Mobile);
            user_account.setEmail(Email);
            user_account.setReesNumber(ReesNumber);
            user_account.setAccountActive(AccountActive);

            user_accountStringDao.create(user_account);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getuseraccounts", (request, response)-> {

            //Create an array with dates
            List<User_Account> user_accountList = user_accountStringDao.queryForAll();
            JSONArray userAccountArray = new JSONArray();
            for (User_Account user_account : user_accountList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("UserID", user_account.getUserID());
                    obj.put("AuthenticationID", user_account.getAuthenticationID());
                    obj.put("UserName", user_account.getUserName());
                    obj.put("Role", user_account.getRole());
                    obj.put("NZHHA_Number", user_account.getNZHHA_Number());
                    obj.put("FirstName", user_account.getFirstName());
                    obj.put("LastName", user_account.getLastName());
                    obj.put("PostalAddress", user_account.getPostalAddress());
                    obj.put("PostalSuburb", user_account.getPostalSuburb());
                    obj.put("PostalCode", user_account.getPostalCode());
                    obj.put("Phone", user_account.getPhone());
                    obj.put("Mobile", user_account.getMobile());
                    obj.put("Email", user_account.getEmail());
                    obj.put("ReesNumber", user_account.getReesNumber());
                    obj.put("AccountActive", user_account.isAccountActive());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userAccountArray.put(obj);
            }
            return userAccountArray.toString();
        });

        //create DAO
        Dao<Install_Type, String> install_typeStringDao = DaoManager.createDao(connectionSource, Install_Type.class);

        //Post request sends date table details to the app
        post("/addinstalltype", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            int InstallTypeID = obj.getInt("InstallTypeID");
            String InstallDescription = obj.getString("InstallDescription");
            int BasePrice = obj.getInt("BasePrice");
            String EmailFromLetter = obj.getString("EmailFromLetter");
            String SiteCheckFile = obj.getString("SiteCheckFile");

            Install_Type install_type = new Install_Type();
            install_type.setInstallTypeID(InstallTypeID);
            install_type.setInstallDescription(InstallDescription);
            install_type.setBasePrice(BasePrice);
            install_type.setEmailFromLetter(EmailFromLetter);
            install_type.setSiteCheckFile(SiteCheckFile);

            install_typeStringDao.create(install_type);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getinstalltypes", (request, response)-> {

            //Create an array with dates
            List<Install_Type> install_typeList = install_typeStringDao.queryForAll();
            JSONArray installTypeArray = new JSONArray();
            for (Install_Type install_type : install_typeList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("InstallTypeID", install_type.getInstallTypeID());
                    obj.put("InstallDescription", install_type.getInstallDescription());
                    obj.put("BasePrice", install_type.getBasePrice());
                    obj.put("EmailFromLetter", install_type.getEmailFromLetter());
                    obj.put("SiteCheckFile", install_type.getSiteCheckFile());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                installTypeArray.put(obj);
            }
            return installTypeArray.toString();
        });
    }

}
