/**
 * Created by Libby Jennings on 6/01/16.
 */


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
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

            Customer customer = new Customer();
            customer.setCustomerID(CustomerID);
            customer.setFirstName(FirstName);
            customer.setLastName(LastName);

            customerDao.create(customer);
            return "OK";

        });

        //Get request for getting customer details from the app
        get("/getcustomers", (request, response)-> {

            //Create an array with coordiantes and plant name
            List<Customer> customers = customerDao.queryForAll();
            JSONArray customerArray = new JSONArray();
            for (Customer customer : customers) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("CustomerID", customer.getCustomerID());
                    obj.put("FirstName", customer.getFirstName());
                    obj.put("LastName", customer.getLastName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                customerArray.put(obj);
            }
            return customerArray.toString();
        });
    }

}
