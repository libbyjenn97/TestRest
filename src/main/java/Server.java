/*
 * Created by Libby Jennings on 6/01/16.
 * Description: Class for handling get and post requests over http from mobile app
 */


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.eclipse.jetty.util.log.Log;
import spark.Request;
import spark.Response;
import org.json.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;
import com.j256.ormlite.table.TableUtils;

public class Server {

    private static final String DB_URL = "jdbc:sqlserver://wch-db.cnfoxyxq90wv.ap-southeast-2.rds.amazonaws.com:47947;databaseName=AWS_WCH_DB";

    // Database credentials
    private static final String USER = "nhfnKGF519hinsd897665465jbsdsf";
    private static final String PASS = "NJiugi7btdjyFbe99n9n09UU";

    private int passedInstallID = 0;

    //stored procedure list
    private static String[] procedure = new String[]{
            "EXEC AWS_WCH_DB.dbo.s_FindAllCustomers", //procedure[0]
            "{call i_UpdateInstallComplete(?,?)}", //procedure[1]
            "{call i_UpdateInstallerNote(?,?)}", //procedure[2]
            "EXEC AWS_WCH_DB.dbo.a_LoginDetails", //procedure[3]
            "EXEC AWS_WCH_DB.dbo.i_GetBookingDetails", //procedure[4]
            "{call s_CreateCustomerSale(?,?,?,?,?,?,?,?,?,?)}" //procedure[5]
    };

    public static void main(String[] args) throws SQLException {

        //Set port number
        port(1997);

        //set-up key store for https
        String keyStoreLocation = "/home/ubuntu/test.jks";
        String keyStorePassword = "wchkeystorep";
        secure(keyStoreLocation, keyStorePassword, null, null);

        //Connect to database, set username and password
        ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL);
        ((JdbcConnectionSource) connectionSource).setUsername(USER);
        ((JdbcConnectionSource) connectionSource).setPassword(PASS);

        //post request for adding a customer and sale into the database from the mobile app
        post("/addcustomersale", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            String FirstName = obj.getString("FirstName");
            String LastName = obj.getString("LastName");
            String PostalAddress = obj.getString("PostalAddress");
            String PostalSuburb = obj.getString("PostalSuburb");
            String PostalCode = obj.getString("PostalCode");
            String Phone = obj.getString("Phone");
            String Mobile = obj.getString("Mobile");
            String Email = obj.getString("Email");
            String SiteAddress = obj.getString("SiteAddress");
            String SiteSuburb = obj.getString("SiteSuburb");

            Connection conn = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Inserting records into the table...");

                CallableStatement statement = conn.prepareCall(procedure[5]);

                statement.setString(1, FirstName);
                statement.setString(2, LastName);
                statement.setString(3, PostalAddress);
                statement.setString(4, PostalSuburb);
                statement.setString(5, PostalCode);
                statement.setString(6, Phone);
                statement.setString(7, Mobile);
                statement.setString(8, Email);
                statement.setString(9, SiteAddress);
                statement.setString(10, SiteSuburb);
                statement.executeUpdate();

                //STEP 4: Execute a query
                System.out.println("Customer and Sale created successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return "OK";

        });

        //Get request for sending customer details to the app
        get("/getcustomers", (request, response)-> {

            Connection conn = null;
            JSONArray customerArray = new JSONArray();
            ArrayList<Customer> customers = new ArrayList<>();
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Getting records from table...");

                PreparedStatement statement = conn.prepareStatement(procedure[0]);
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerID(result.getInt("CustomerID"));
                    customer.setFirstName(result.getString("FirstName"));
                    customer.setLastName(result.getString("LastName"));
                    customer.setPostalAddress(result.getString("PostalAddress"));
                    customer.setPostalSuburb(result.getString("PostalSuburb"));
                    customer.setPostalCode(result.getString("PostalCode"));
                    customer.setPhone(result.getString("Phone"));
                    customer.setMobile(result.getString("Mobile"));
                    customer.setEmail(result.getString("Email"));
                    customers.add(customer);
                }
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    customerArray.put(obj);
                }

                //STEP 4: Execute a query
                System.out.println("Customers found successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return customerArray.toString();

        });

        //Get request for sending booking details to the app
        get("/getbookingdetails", (request, response)-> {

            Connection conn = null;
            JSONArray bookingArray = new JSONArray();
            ArrayList<Booking> bookings = new ArrayList<>();
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Getting records from table...");

                //sql server procedure
                PreparedStatement statement = conn.prepareStatement(procedure[4]);
                ResultSet result = statement.executeQuery();

                while(result.next()) {
                    Booking booking = new Booking();
                    booking.setCustomerID(result.getInt("CustomerID"));
                    booking.setFirstName(result.getString("FirstName"));
                    booking.setLastName(result.getString("LastName"));
                    booking.setPostalAddress(result.getString("PostalAddress"));
                    booking.setPostalSuburb(result.getString("PostalSuburb"));
                    booking.setPostalCode(result.getString("PostalCode"));
                    booking.setPhone(result.getString("Phone"));
                    booking.setMobile(result.getString("Mobile"));
                    booking.setEmail(result.getString("Email"));
                    booking.setSaleID(result.getInt("SaleID"));
                    booking.setInstallTypeID(result.getInt("InstallTypeID"));
                    booking.setSiteAddress(result.getString("SiteAddress"));
                    booking.setSiteSuburb(result.getString("SiteSuburb"));
                    booking.setInstallID(result.getInt("InstallID"));
                    booking.setInstallerID(result.getString("InstallerID"));
                    booking.setFireID(result.getString("FireID"));
                    booking.setStockList(result.getString("StockList"));
                    booking.setNoteToInstaller(result.getString("NoteToInstaller"));
                    booking.setInstallComplete(result.getBoolean("InstallComplete"));
                    booking.setInstallerNote(result.getString("InstallerNote"));
                    booking.setFireType(result.getString("FireType"));
                    booking.setInstallDescription(result.getString("InstallDescription"));
                    booking.setInstallDate(result.getDate("InstallDate"));
                    booking.setInstallTime(result.getString("InstallTime"));
                    booking.setUserID(result.getInt("UserID"));
                    bookings.add(booking);
                }
                for (Booking booking : bookings) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("CustomerID", booking.getCustomerID());
                        obj.put("FirstName", booking.getFirstName());
                        obj.put("LastName", booking.getLastName());
                        obj.put("PostalAddress", booking.getPostalAddress());
                        obj.put("PostalSuburb", booking.getPostalSuburb());
                        obj.put("PostalCode", booking.getPostalCode());
                        obj.put("Phone", booking.getPhone());
                        obj.put("Mobile", booking.getMobile());
                        obj.put("Email", booking.getEmail());
                        obj.put("SaleID", booking.getSaleID());
                        obj.put("InstallTypeID", booking.getInstallTypeID());
                        obj.put("SiteAddress", booking.getSiteAddress());
                        obj.put("SiteSuburb", booking.getSiteSuburb());
                        obj.put("InstallID", booking.getInstallID());
                        obj.put("InstallerID", booking.getInstallerID());
                        obj.put("FireID", booking.getFireID());
                        obj.put("StockList", booking.getStockList());
                        obj.put("NoteToInstaller", booking.getNoteToInstaller());
                        obj.put("InstallComplete", booking.isInstallComplete());
                        obj.put("InstallerNote", booking.getInstallerNote());
                        obj.put("FireType", booking.getFireType());
                        obj.put("InstallDescription", booking.getInstallDescription());
                        obj.put("InstallDate", booking.getInstallDate());
                        obj.put("InstallTime", booking.getInstallTime());
                        obj.put("UserID", booking.getUserID());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    bookingArray.put(obj);
                }

                //STEP 4: Execute a query
                System.out.println("Bookings found successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return bookingArray.toString();

        });

        //Get request for getting date table details from the app
        get("/getuseraccounts", (request, response)-> {

            Connection conn = null;
            JSONArray userAccountArray = new JSONArray();
            ArrayList<User_Account> user_accountList = new ArrayList<>();
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Getting records from table...");

            //sql server procedure
            PreparedStatement statement = conn.prepareStatement(procedure[3]);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                User_Account user_account = new User_Account();
                user_account.setUserID(result.getInt("UserID"));
                user_account.setAuthenticationID(result.getInt("AuthenticationID"));
                user_account.setUserName(result.getString("UserName"));
                user_account.setRoleType(result.getString("RoleType"));
                user_account.setInstall(result.getBoolean("Install"));
                user_account.setAccountActive(result.getBoolean("AccountActive"));
                user_account.setPasswordHash(result.getString("PasswordHash"));
                user_accountList.add(user_account);
            }
            for (User_Account user_account : user_accountList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("UserID", user_account.getUserID());
                    obj.put("AuthenticationID", user_account.getAuthenticationID());
                    obj.put("UserName", user_account.getUserName());
                    obj.put("RoleType", user_account.getRoleType());
                    obj.put("Install", user_account.isInstall());
                    obj.put("AccountActive", user_account.isAccountActive());
                    obj.put("PasswordHash", user_account.getPasswordHash());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userAccountArray.put(obj);
            }
            return userAccountArray.toString();
        });


        //Post request updates installation status from mobile app to database
        post("/addInstallComplete", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);

            Boolean InstallComplete = obj.getBoolean("InstallComplete");
            int InstallID = obj.getInt("InstallID");

            Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                //STEP 4: Execute a query
                System.out.println("Inserting records into the table...");

                int installCompleteBit = 0;

                if(InstallComplete){
                    installCompleteBit = 1;
                }
                else {
                    installCompleteBit = 0;
                }

                System.out.println(installCompleteBit);

                //sql server procedure
                CallableStatement statement = conn.prepareCall(procedure[1]);
                statement.setString(2, String.valueOf(InstallComplete));
                statement.setString(1, String.valueOf(InstallID));
                statement.executeUpdate();

                //STEP 4: Execute a query
                System.out.println("Install completion updated successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return "OK";

        });

        //Post request updates install with an installer note from the mobile app to the db
        post("/addinstallernote", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);

            String InstallerNote = obj.getString("InstallerNote");
            int InstallID = obj.getInt("InstallID");

            Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                //STEP 4: Execute a query
                System.out.println("Inserting records into the table...");

                //sql server procedure
                CallableStatement statement = conn.prepareCall(procedure[2]);
                statement.setString(2, InstallerNote);
                statement.setString(1, String.valueOf(InstallID));
                statement.executeUpdate();

                //STEP 4: Execute a query
                System.out.println("Install note added successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return "OK";

        });
    }

}
