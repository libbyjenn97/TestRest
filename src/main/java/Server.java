/**
 * Created by Libby Jennings on 6/01/16.
 */


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;
import com.sun.tools.corba.se.idl.constExpr.Or;
import spark.Request;
import spark.Response;
import org.json.*;
import java.text.SimpleDateFormat;
import java.sql.Date;
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

        //create DAO
        Dao<Sale, String> saleStringDao = DaoManager.createDao(connectionSource, Sale.class);

        //Post request sends date table details to the app
        post("/addsale", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            int SaleID = obj.getInt("SaleID");
            int CustomerID = obj.getInt("CustomerID");
            int UserID = obj.getInt("UserID");
            int InstallTypeID = obj.getInt("InstallTypeID");
            String SiteAddress = obj.getString("SiteAddress");
            String SiteSuburb = obj.getString("SiteSuburb");
            String SaleStatus = obj.getString("SaleStatus");
            String Fire = obj.getString("Fire");
            int Price = obj.getInt("Price");
            boolean SiteCheckBooked = obj.getBoolean("SiteCheckBooked");

            String SiteCheckDateString = obj.getString("SiteCheckDate");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date DateValueUtil = sdf1.parse(SiteCheckDateString);
            java.sql.Date SiteCheckDateSql = new java.sql.Date(DateValueUtil.getTime());

            String SiteCheckTimeString = obj.getString("SiteCheckTime");
            //java.sql.Time SiteCheckTimeSql = java.sql.Time.valueOf(SiteCheckTimeString);
            /*SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
            java.util.Date TimeValueUtil = sdf2.parse(SiteCheckTimeString);
            java.sql.Time SiteCheckTimeSql = new java.sql.Time(TimeValueUtil.getTime());*/

            int SiteCheckBy = obj.getInt("SiteCheckBy");
            int SalesPerson = obj.getInt("SalesPerson");

            String EstimationDateString = obj.getString("EstimationDate");
            java.util.Date EstimationDateValueUtil = sdf1.parse(EstimationDateString);
            java.sql.Date EstimationDateSql = new java.sql.Date(EstimationDateValueUtil.getTime());

            String QuoteNumber = obj.getString("QuoteNumber");
            String SiteCheckPath = obj.getString("SiteCheckPath");
            String QuotePath = obj.getString("QuotePath");
            String PhotoPath = obj.getString("PhotoPath");

            String FollowUpDateString = obj.getString("FollowUpDate");
            java.util.Date FollowUpDateValueUtil = sdf1.parse(FollowUpDateString);
            java.sql.Date FollowUpDateSql = new java.sql.Date(FollowUpDateValueUtil.getTime());

            Sale sale = new Sale();
            sale.setCustomerID(CustomerID);
            sale.setUserID(UserID);
            sale.setInstallTypeID(InstallTypeID);
            sale.setSiteAddress(SiteAddress);
            sale.setSiteSuburb(SiteSuburb);
            sale.setSaleStatus(SaleStatus);
            sale.setFire(Fire);
            sale.setPrice(Price);
            sale.setSiteCheckBooked(SiteCheckBooked);
            sale.setSiteCheckDate(SiteCheckDateSql);
            sale.setSiteCheckTime(SiteCheckTimeString);
            sale.setSiteCheckBy(SiteCheckBy);
            sale.setSalesPerson(SalesPerson);
            sale.setEstimationDate(EstimationDateSql);
            sale.setQuoteNumber(QuoteNumber);
            sale.setSiteCheckPath(SiteCheckPath);
            sale.setQuotePath(QuotePath);
            sale.setPhotoPath(PhotoPath);
            sale.setFollowUpDate(FollowUpDateSql);

            saleStringDao.create(sale);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getsales", (request, response)-> {

            //Create an array with dates
            List<Sale> saleList = saleStringDao.queryForAll();
            JSONArray saleArray = new JSONArray();
            for (Sale sale : saleList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("SaleID", sale.getSaleID());
                    obj.put("CustomerID", sale.getCustomerID());
                    obj.put("UserID", sale.getUserID());
                    obj.put("InstallTypeID", sale.getInstallTypeID());
                    obj.put("SiteAddress", sale.getSiteAddress());
                    obj.put("SiteSuburb", sale.getSiteSuburb());
                    obj.put("SaleStatus", sale.getSaleStatus());
                    obj.put("Fire", sale.getFire());
                    obj.put("Price", sale.getPrice());
                    obj.put("SiteCheckBooked", sale.isSiteCheckBooked());
                    obj.put("SiteCheckDate", sale.getSiteCheckDate());
                    obj.put("SiteCheckTime", sale.getSiteCheckTime());
                    obj.put("SiteCheckBy", sale.getSiteCheckBy());
                    obj.put("SalesPerson", sale.getSalesPerson());
                    obj.put("EstimationDate", sale.getEstimationDate());
                    obj.put("QuoteNumber", sale.getQuoteNumber());
                    obj.put("SiteCheckPath", sale.getSiteCheckPath());
                    obj.put("QuotePath", sale.getQuotePath());
                    obj.put("PhotoPath", sale.getPhotoPath());
                    obj.put("FolllowUpDate", sale.getFollowUpDate());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                saleArray.put(obj);
            }
            return saleArray.toString();
        });

        //create DAO
        Dao<Install, String> installStringDao = DaoManager.createDao(connectionSource, Install.class);

        //Post request sends date table details to the app
        post("/addinstall", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            //int InstallID = obj.getInt("InstallID");
            int SaleID = obj.getInt("SaleID");
            String InstallStatus = obj.getString("InstallStatus");
            //String InvoicePath = obj.getString("InvoicePath");
            String SiteCheckPath = obj.getString("SiteCheckPath");
            String PhotoPath = obj.getString("PhotoPath");
            boolean OrderChecked = obj.getBoolean("OrderChecked");
            String InstallerID = obj.getString("InstallerID");

            String InstallDateString = obj.getString("InstallDate");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date DateValueUtil = sdf1.parse(InstallDateString);
            java.sql.Date InstallDateSql = new java.sql.Date(DateValueUtil.getTime());

            String InstallTime = obj.getString("InstallTime");
            int PartsReady = obj.getInt("PartsReady");
            String NoteToInstaller = obj.getString("NoteToInstaller");
            boolean InstallComplete = obj.getBoolean("InstallComplete");
            String InstallerNote = obj.getString("InstallerNote");

            Install install = new Install();
            //install.setInstallID(InstallID);
            install.setSaleID(SaleID);
            install.setInstallStatus(InstallStatus);
            //install.setInvoicePath(InvoicePath);
            install.setSiteCheckPath(SiteCheckPath);
            install.setPhotoPath(PhotoPath);
            install.setOrderChecked(OrderChecked);
            install.setInstallerID(InstallerID);
            install.setInstallDate(InstallDateSql);
            install.setInstallTime(InstallTime);
            install.setPartsReady(PartsReady);
            install.setNoteToInstaller(NoteToInstaller);
            install.setInstallComplete(InstallComplete);
            install.setInstallerNote(InstallerNote);


            installStringDao.create(install);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getinstalls", (request, response)-> {

            //Create an array with dates
            List<Install> installList = installStringDao.queryForAll();
            JSONArray installArray = new JSONArray();
            for (Install install : installList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("InstallID", install.getInstallID());
                    obj.put("SaleID", install.getSaleID());
                    obj.put("InstallStatus", install.getInstallStatus());
                    //obj.put("InvoicePath", install.getInvoicePath());
                    obj.put("SiteCheckPath", install.getSiteCheckPath());
                    obj.put("PhotoPath", install.getPhotoPath());
                    obj.put("OrderChecked", install.isOrderChecked());
                    obj.put("InstallerID", install.getInstallerID());
                    obj.put("InstallDate", install.getInstallDate());
                    obj.put("InstallTime", install.getInstallTime());
                    obj.put("PartsReady", install.getPartsReady());
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
        Dao<Fire, String> fireStringDao = DaoManager.createDao(connectionSource, Fire.class);

        //Post request sends date table details to the app
        post("/addfire", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);
            String FireID = obj.getString("FireID");
            String FireType = obj.getString("FireType");
            String Make = obj.getString("Make");
            String Model = obj.getString("Model");
            String Fuel = obj.getString("Fuel");
            String ECAN = obj.getString("ECAN");
            String Nelson = obj.getString("Nelson");
            String Life = obj.getString("Life");

            Fire fire = new Fire();
            fire.setFireID(FireID);
            fire.setFireType(FireType);
            fire.setMake(Make);
            fire.setModel(Model);
            fire.setFuel(Fuel);
            fire.setECAN(ECAN);
            fire.setNelson(Nelson);
            fire.setLife(Life);

            fireStringDao.create(fire);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getfires", (request, response)-> {

            //Create an array with dates
            List<Fire> fireList = fireStringDao.queryForAll();
            JSONArray fireArray = new JSONArray();
            for (Fire fire : fireList) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("FireID", fire.getFireID());
                    obj.put("FireType", fire.getFireType());
                    obj.put("Make", fire.getMake());
                    obj.put("Model", fire.getModel());
                    obj.put("Fuel", fire.getFuel());
                    obj.put("ECAN", fire.getECAN());
                    obj.put("Nelson", fire.getNelson());
                    obj.put("Life", fire.getLife());

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


        //create DAO
        Dao<Schedule, String> scheduleStringDao = DaoManager.createDao(connectionSource, Schedule.class);

        //Post request sends date table details to the app
        post("/addschedule", (Request request, Response response) -> {
            String data = request.body();
            System.out.println(data);

            JSONObject obj = new JSONObject(data);

            String InstallDateString = obj.getString("InstallDate");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date DateValueUtil = sdf1.parse(InstallDateString);
            java.sql.Date InstallDateSql = new java.sql.Date(DateValueUtil.getTime());

            String InstallTime = obj.getString("InstallTime");
            int UserID = obj.getInt("UserID");
            int InstallID = obj.getInt("InstallID");

            Schedule schedule = new Schedule();
            schedule.setInstallDate(InstallDateSql);
            schedule.setInstallTime(InstallTime);
            schedule.setUserID(UserID);
            schedule.setInstallID(InstallID);

            scheduleStringDao.create(schedule);
            return "OK";

        });

        //Get request for getting date table details from the app
        get("/getschedules", (request, response)-> {

            //Create an array with dates
            List<Schedule> scheduleList = scheduleStringDao.queryForAll();
            JSONArray scheduleArray = new JSONArray();
            for (Schedule schedule : scheduleList) {
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
