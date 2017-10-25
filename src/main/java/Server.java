/**
 * Created by Libby Jennings on 6/01/16.
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

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:sqlserver://wchdb.cnfoxyxq90wv.ap-southeast-2.rds.amazonaws.com:1433;databaseName=AWS_WCH_DB";

    //  Database credentials
    static final String USER = "Khgv92367hdkfug9";
    static final String PASS = "Locei02h84b5KJUVaW";

    public int passedInstallID = 0;

    private static String[] procedure = new String[]{
            "EXEC AWS_WCH_DB.dbo.s_FindAllCustomers", //procedure[0]
            "EXEC AWS_WCH_DB.dbo.s_CreateCustomerSale", //procedure[1]
            "{call s_CreateCustomerSale(?,?,?,?,?,?,?,?,?,?)}", //procedure[2]
            "EXEC AWS_WCH_DB.dbo.i_FindSelectedBookingDetails", //procedure[3]
            "{call i_FindSelectedBookingDetails(?)}", //procedure[4]
            "EXEC AWS_WCH_DB.dbo.i_GetCalendarInstalls", //procedure[5]
            "EXEC AWS_WCH_DB.dbo.s_FindCalendarSales", //procedure[6]
            "EXEC AWS_WCH_DB.dbo.s_FindScheduledEvents", //procedure[7]
            "EXEC AWS_WCH_DB.dbo.s_getBookingInstallType", //procedure[8]
            "EXEC AWS_WCH_DB.dbo.i_GetCalendarFireType", //procedure[9]
            "{call i_UpdateInstallComplete(?,?)}", //procedure[10]
            "{call i_UpdateInstallerNote(?,?)}", //procedure[11]
            "EXEC AWS_WCH_DB.dbo.a_LoginDetails", //procedure[12]
            "EXEC AWS_WCH_DB.dbo.i_GetBookingDetails", //procedure[13]
    };

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

        post("/addcustomersale", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            String ReesCode = "";

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

            try {
                ReesCode = obj.getString("ReesCode");
            }
            catch (org.json.JSONException e){
                System.out.println("null");
            }

            if(ReesCode.isEmpty()){
                ReesCode = "NULL";
            }

            Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Inserting records into the table...");

                /*PreparedStatement statement = conn.prepareStatement(procedure[1]);
                ResultSet result = statement.executeQuery();*/

                CallableStatement statement = conn.prepareCall(procedure[2]);

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
            Statement stmt = null;
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

        //Get request for sending customer details to the app
        get("/getbookingdetails", (request, response)-> {

            Connection conn = null;
            Statement stmt = null;
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

                PreparedStatement statement = conn.prepareStatement(procedure[13]);
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

        post("/postInstallID", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);


            JSONObject obj = new JSONObject(data);
            int InstallID = obj.getInt("InstallID");
            Server server = new Server();
            server.passedInstallID = InstallID;

            return InstallID;
        });



        //Get request for sending customer details to the app
        /*get("/getbookingdetails", (request, response)-> {

            Connection conn = null;
            Statement stmt = null;
            JSONArray bookingDetailsArray = new JSONArray();
            ArrayList<Customer> customers = new ArrayList<>();
            ArrayList<Fire> fires = new ArrayList<>();
            ArrayList<Install_Type> install_types = new ArrayList<>();
            ArrayList<Install> installs = new ArrayList<>();
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Getting records from tables...");

                //PreparedStatement statement = conn.prepareStatement(procedure[3]);

                Server server = new Server();

                int installID = 3244;

                CallableStatement callstatement = conn.prepareCall(procedure[4]);
                callstatement.setString(1, String.valueOf(installID));

                //PreparedStatement statement = conn.prepareStatement(procedure[3]);
                ResultSet result = callstatement.executeQuery();

                while(result.next()) {
                    Customer customer = new Customer();
                    Fire fire = new Fire();
                    Install_Type install_type = new Install_Type();
                    Install install = new Install();
                    customer.setFirstName(result.getString("FirstName"));
                    customer.setLastName(result.getString("LastName"));
                    customer.setPostalAddress(result.getString("PostalAddress"));
                    customer.setPostalSuburb(result.getString("PostalSuburb"));
                    customer.setPostalCode(result.getString("PostalCode"));
                    customer.setPhone(result.getString("Phone"));
                    customer.setMobile(result.getString("Mobile"));
                    customer.setEmail(result.getString("Email"));
                    fire.setFireType(result.getString("FireType"));
                    install_type.setInstallDescription(result.getString("InstallDescription"));
                    install.setInstallComplete(result.getBoolean("InstallComplete"));

                    customers.add(customer);
                    fires.add(fire);
                    install_types.add(install_type);
                    installs.add(install);

                }
                JSONObject obj = new JSONObject();
                for (Customer customer : customers) {
                    try {
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
                    //bookingDetailsArray.put(obj);
                }

                for (Fire fire : fires) {
                    try {
                        obj.put("FireType", fire.getFireType());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //bookingDetailsArray.put(obj);
                }


                for (Install install : installs) {
                    try {
                        obj.put("InstallComplete", install.isInstallComplete());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //bookingDetailsArray.put(obj);
                }

                for (Install_Type install_type : install_types) {
                    try {
                        obj.put("InstallDescription", install_type.getInstallDescription());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    bookingDetailsArray.put(obj);
                }

                //STEP 4: Execute a query
                System.out.println("Booking details found successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return bookingDetailsArray.toString();

        });*/

        //create DAO
        Dao<Customer, String> newCustomerDao = DaoManager.createDao(connectionSource, Customer.class);

        //Get request for sending customer details to the app
        get("/getnewcustomer", (request, response)-> {

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                customerArray.put(obj);
            }
            return customerArray.toString();
        });

        //create DAO
        Dao<Sale, String> saleStringDao = DaoManager.createDao(connectionSource, Sale.class);

        //Post request sends date table details to the app
        post("/addsale", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            String ReesCode = "";

            JSONObject obj = new JSONObject(data);
            String CustomerID = obj.getString("CustomerID");
            String SiteAddress = obj.getString("SiteAddress");
            String SiteSuburb = obj.getString("SiteSuburb");

            Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                //STEP 4: Execute a query
                System.out.println("Inserting records into the table...");

                PreparedStatement statement = conn.prepareStatement("insert into Sale (CustomerID, SiteAddress, SiteSuburb ) values (?, ?, ?)");
                statement.setString(1, CustomerID);
                statement.setString(2, SiteAddress);
                statement.setString(3, SiteSuburb);
                statement.executeUpdate();

                //STEP 4: Execute a query
                System.out.println("Install updated successfully");

            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getsales", (request, response)-> {

            Connection conn = null;
            Statement stmt = null;
            JSONArray saleArray = new JSONArray();
            ArrayList<Sale> sales = new ArrayList<>();
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Getting records from table...");

                PreparedStatement statement = conn.prepareStatement(procedure[6]);
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                    Sale sale = new Sale();
                    sale.setSaleID(result.getInt("SaleID"));
                    sale.setCustomerID(result.getInt("CustomerID"));
                    sale.setInstallTypeID(result.getInt("InstallTypeID"));
                    sale.setSiteAddress(result.getString("SiteAddress"));
                    sale.setSiteSuburb(result.getString("SiteSuburb"));
                    sales.add(sale);
                }
            for (Sale sale : sales) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("SaleID", sale.getSaleID());
                    obj.put("CustomerID", sale.getCustomerID());
                    obj.put("InstallTypeID", sale.getInstallTypeID());
                    obj.put("SiteAddress", sale.getSiteAddress());
                    obj.put("SiteSuburb", sale.getSiteSuburb());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                saleArray.put(obj);
            }
            return saleArray.toString();
        });

        //create DAO
        Dao<DateTable, String> dateTableDao = DaoManager.createDao(connectionSource, DateTable.class);

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

        //Get request for getting date table details from the app
        get("/getuseraccounts", (request, response)-> {

            Connection conn = null;
            Statement stmt = null;
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

            PreparedStatement statement = conn.prepareStatement(procedure[12]);
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


        //Post request sends date table details to the app
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

                CallableStatement statement = conn.prepareCall(procedure[10]);
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

        //Post request sends date table details to the app
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


                CallableStatement statement = conn.prepareCall(procedure[11]);
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

        //Get request for getting date table details from the app
        get("/getinstalls", (request, response)-> {


            Connection conn = null;
            Statement stmt = null;
            ArrayList<Install> installs = new ArrayList<>();
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Getting records from table...");

                PreparedStatement statement = conn.prepareStatement(procedure[5]);
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                    Install install = new Install();
                    install.setInstallID(result.getInt("InstallID"));
                    install.setSaleID(result.getInt("SaleID"));
                    install.setInstallerID(result.getString("InstallerID"));
                    install.setFireID(result.getString("FireID"));
                    install.setStockList(result.getString("StockList"));
                    install.setNoteToInstaller(result.getString("NoteToInstaller"));
                    install.setInstallComplete(result.getBoolean("InstallComplete"));
                    install.setInstallerNote(result.getString("InstallerNote"));
                    installs.add(install);
                }
            JSONArray installArray = new JSONArray();
            for (Install install : installs) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("InstallID", install.getInstallID());
                    obj.put("SaleID", install.getSaleID());
                    obj.put("InstallerID", install.getInstallerID());
                    obj.put("FireID", install.getFireID());
                    obj.put("StockList", install.getStockList());
                    obj.put("NoteToInstaller", install.getNoteToInstaller());
                    obj.put("InstallComplete", install.isInstallComplete());
                    obj.put("InstallerNote", install.getInstallerNote());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                installArray.put(obj);
            }
            return installArray.toString();
        });


        //create DAO
        Dao<Install_Type, String> install_typeStringDao = DaoManager.createDao(connectionSource, Install_Type.class);
        //Get request for getting date table details from the app
        get("/getinstalltypes", (request, response)-> {


            Connection conn = null;
            Statement stmt = null;
            ArrayList<Install_Type> install_types = new ArrayList<>();
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Getting records from table...");

            PreparedStatement statement = conn.prepareStatement(procedure[8]);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Install_Type install_type = new Install_Type();
                install_type.setInstallTypeID(result.getInt("InstallTypeID"));
                install_type.setInstallDescription(result.getString("InstallTypeDescription"));
                install_types.add(install_type);
            }
            JSONArray installArray = new JSONArray();
            for (Install_Type install_type : install_types) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("InstallTypeID", install_type.getInstallTypeID());
                    obj.put("InstallDescription", install_type.getInstallDescription());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                installArray.put(obj);
            }
            return installArray.toString();
        });


        //Get request for getting date table details from the app
        get("/getfires", (request, response)-> {

            Connection conn = null;
            Statement stmt = null;
            ArrayList<Fire> fireList = new ArrayList<>();
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Getting records from table...");

            PreparedStatement statement = conn.prepareStatement(procedure[9]);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Fire fire = new Fire();
                fire.setFireID(result.getString("FireID"));
                fire.setFireType(result.getString("FireType"));
                fireList.add(fire);
            }
            JSONArray fireArray = new JSONArray();
            for (Fire fire : fireList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("FireID", fire.getFireID());
                    obj.put("FireType", fire.getFireType());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                fireArray.put(obj);
            }
            return fireArray.toString();
        });

        //create DAO
        Dao<Follow_Up_Comment, String> follow_up_commentStringDao = DaoManager.createDao(connectionSource, Follow_Up_Comment.class);

        //Post request sends date table details to the app
        post("/addfollowupcomment", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            int CommentID = obj.getInt("CommentID");
            int SaleID = obj.getInt("SaleID");
            String Comment = obj.getString("Comment");
            String Time_Stamp = obj.getString("Time_Stamp");

            Follow_Up_Comment follow_up_comment = new Follow_Up_Comment();
            follow_up_comment.setCommentID(CommentID);
            follow_up_comment.setSaleID(SaleID);
            follow_up_comment.setComment(Comment);
            follow_up_comment.setTime_Stamp(Time_Stamp);

            follow_up_commentStringDao.create(follow_up_comment);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getfollowupcomments", (request, response)-> {

            //Create an array with dates
            List<Follow_Up_Comment> follow_up_commentList = follow_up_commentStringDao.queryForAll();
            JSONArray follow_up_commentArray = new JSONArray();
            for (Follow_Up_Comment follow_up_comment : follow_up_commentList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("CommentID", follow_up_comment.getCommentID());
                    obj.put("SaleID", follow_up_comment.getSaleID());
                    obj.put("Comment", follow_up_comment.getComment());
                    obj.put("Time_Stamp", follow_up_comment.getTime_Stamp());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                follow_up_commentArray.put(obj);
            }
            return follow_up_commentArray.toString();
        });


        //Get request for getting date table details from the app
        get("/getschedules", (request, response)-> {

            Connection conn = null;
            Statement stmt = null;
            ArrayList<Schedule> schedules = new ArrayList<>();
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Getting records from table...");

            PreparedStatement statement = conn.prepareStatement(procedure[7]);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Schedule schedule = new Schedule();
                schedule.setInstallDate(result.getDate("InstallDate"));
                schedule.setInstallTime(result.getString("InstallTime"));
                schedule.setInstallID(result.getInt("InstallID"));
                schedule.setUserID(result.getInt("UserID"));
                schedules.add(schedule);
            }
            JSONArray scheduleArray = new JSONArray();
            for (Schedule schedule : schedules) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("InstallDate", schedule.getInstallDate());
                    obj.put("InstallTime", schedule.getInstallTime());
                    obj.put("UserID", schedule.getUserID());
                    obj.put("InstallID", schedule.getInstallID());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                scheduleArray.put(obj);
            }
            return scheduleArray.toString();
        });
    }

}
