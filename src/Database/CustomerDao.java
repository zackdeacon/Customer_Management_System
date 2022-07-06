package Database;

import Model.customer;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;



public class CustomerDao {

    /**
     * Collects specific Customer data from the database and returns an Observable list.
     *   @param list Observable list to hold Appointments.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList getAllCustomer(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();

        String sqlStmt = "SELECT * from customers JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
        Query.makeQuery(sqlStmt);
        customer customerResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int customer_ID = result.getInt("Customer_ID");
            String customer_Name = result.getString("Customer_Name");
            String Address = result.getString("Address");
            String postal_Code = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            String division = result.getString("Division");
            String country = result.getString("Country");
            int country_ID = result.getInt("Country_ID");
            int division_ID = result.getInt("Division_ID");
            customerResult = new customer(customer_ID, customer_Name, Address, postal_Code, phone, division, country, country_ID, division_ID);
            //System.out.println(customerResult.getCustomer_Name());
            list.addAll(customerResult);
        }

        return list;

    }

    /**
     * Updates and saves customer records in the database.
     *   @param ID ID variable.
     *   @param name name variable.
     *   @param address address variable.
     *   @param postalCode Postal Code variable.
     *   @param phone Phone Number variable.
     *   @param user User variable.
     *   @param divisionID LDivision ID variable.
     */
    public static void updateCustomerSQL(int ID, String name, String address, String postalCode, String phone, String user, int divisionID) {
        JDBC.getConnection();
        String sqlStmt = "UPDATE customers SET Customer_Name = '" + name + "', Address = '" + address + "', Postal_Code = '" + postalCode + "', Phone = '" + phone + "', Last_Update = sysdate(), Last_Updated_By = '" + user + "', Division_ID = '" + divisionID + "' WHERE Customer_ID = '" + ID + "';";
        Query.makeQuery(sqlStmt);
    }

    /**
     * Creates and saves customer records in the database.
     *   @param customerID ID variable.
     *   @param name name variable.
     *   @param address address variable.
     *   @param postalCode Postal Code variable.
     *   @param phone Phone Number variable.
     *   @param user User variable.
     *   @param divisionID LDivision ID variable.
     */
    public static void createCustomerSQL(int customerID, int divisionID, String name, String address, String postalCode, String phone, String user) {
        JDBC.getConnection();
        String sqlStmt = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES ('"+ name +"', '"+ address +"', '"+ postalCode +"','"+ phone +"', sysdate(), '"+ user +"', sysdate(), '"+ user +"', "+ divisionID +");";
        Query.makeQuery(sqlStmt);
    }


    /**
     * Deletes Customer record that matches passed Customer ID from the database.
     *   @param custID Customer ID variable.
     */
    public static void deleteCustomer(int custID) {
        JDBC.getConnection();
        String sqlstmt = "DELETE FROM customers WHERE Customer_ID = '" + custID + "';";
        Query.makeQuery(sqlstmt);
    }


}
