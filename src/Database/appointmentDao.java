package Database;

import Model.appointment;
import Model.contact;
import Model.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class appointmentDao {

    /**
     * Collects specific Appointment data from the database and returns an Observable list.
     *   @param list Observable list to hold Appointments.
     *   @param selectedStart LocalDateTime variable.
     *   @param selectedEnd LocalDateTime variable.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList getAllAppt(ObservableList list, LocalDateTime selectedStart, LocalDateTime selectedEnd) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM appointments WHERE Start > '"+ Timestamp.valueOf(selectedStart) +"' AND End < '"+ Timestamp.valueOf(selectedEnd) +"';";
        Query.makeQuery(sqlStmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactID = result.getInt("Contact_ID");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            appointmentResult = new appointment(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
            list.addAll(appointmentResult);
        }
        return list;
    }

    /**
     * Collects specific Contact data from the database and returns an Observable list.
     *   @param list PObservable list to hold Appointments.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList getAllContacts(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM contacts;";
        Query.makeQuery(sqlStmt);
        contact contactResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int contactID = result.getInt("Contact_ID");
            String name = result.getString("Contact_Name");
            String email = result.getString("Email");
            contactResult = new contact(contactID, name, email);
            list.addAll(contactResult);
        }
        return list;
    }

    /**
     * Collects specific User data from the database and returns an Observable list.
     *   @param list PObservable list to hold Appointments.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList getAllUser(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM users;";
        Query.makeQuery(sqlStmt);
        user userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int userID = result.getInt("User_ID");
            String name = result.getString("User_Name");
            String password = result.getString("Password");
            userResult = new user(userID, name, password);
            list.addAll(userResult);
        }
        return list;
    }

    /**
     * Collects all Appointment data from the database and returns an Observable list.
     *   @param list Observable list to hold Appointments.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList getAll(ObservableList list) throws SQLException, Exception {

        JDBC.getConnection();
        String sqlstmt = "SELECT * FROM appointments;";
        Query.makeQuery(sqlstmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactID = result.getInt("Contact_ID");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            appointmentResult = new appointment(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
            list.addAll(appointmentResult);
        }
        return list;
    }

    /**
     * Inserts and saves Appointment data to the database and returns boolean confirmation.
     *   @param contactID Variable to hold contact ID.
     *   @param userID Variable to hold User ID.
     *   @param custID Variable to hold Customer ID.
     *   @param user Variable to hold User Name.
     *   @param end LocalDateTime variable.
     *   @param start LocalDateTime variable.
     *   @param type Variable to hold Type.
     *   @param location Variable to hold Location.
     *   @param description Variable to hold Description.
     *   @param title Variable to hold Title.
     *   @throws Exception From FXMLLoader.
     */
    public static boolean insertAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String user, int custID, int userID, int contactID) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, " +
                "Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES( ?, ?, " +
                "?, ?, ?,?, now(),?, now(),?, ?, ?,?);";
        JDBC.makePreparedStatement(sqlStmt, JDBC.getConnection());
        PreparedStatement query = JDBC.getPreparedStatement();
        query.setString(1, title);
        query.setString(2, description);
        query.setString(3, location);
        query.setString(4, type);
        query.setTimestamp(5, Timestamp.valueOf(start));
        query.setTimestamp(6, Timestamp.valueOf(end));
        query.setString(7, user);
        query.setString(8, user);
        query.setInt(9, custID);
        query.setInt(10, userID);
        query.setInt(11, contactID);
        try {
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Updates and saves Appointment data to the database and returns boolean confirmation.
     *   @param contactID Variable to hold contact ID.
     *   @param userID Variable to hold User ID.
     *   @param custID Variable to hold Customer ID.
     *   @param userName Variable to hold User Name.
     *   @param end LocalDateTime variable.
     *   @param start LocalDateTime variable.
     *   @param type Variable to hold Type.
     *   @param location Variable to hold Location.
     *   @param description Variable to hold Description.
     *   @param title Variable to hold Title.
     *   @throws Exception From FXMLLoader.
     */
    public static void updateAppointmentSQL(int appID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String userName, int custID, int userID, int contactID) throws SQLException, Exception{
        JDBC.getConnection();
        String sqlStmt = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, " +
                "Type = ?, Start = ?, End = ?, " +
                "Last_Update = now(), Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?;";
        JDBC.makePreparedStatement(sqlStmt, JDBC.getConnection());
        PreparedStatement query = JDBC.getPreparedStatement();
        query.setString(1, title);
        query.setString(2, description);
        query.setString(3, location);
        query.setString(4, type);
        query.setTimestamp(5, Timestamp.valueOf(start));
        query.setTimestamp(6, Timestamp.valueOf(end));
        query.setString(7, userName);
        query.setInt(8, custID);
        query.setInt(9, userID);
        query.setInt(10, contactID);
        query.setInt(11, appID);
        try {
            query.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Deletes Appointment that has matching Appointment ID from the database.
     *   @param apptID Variable to hold Appointment ID.
     */
    public static void deleteAppointment(int apptID) {
        JDBC.getConnection();
        String sqlstmt = "DELETE FROM appointments WHERE Appointment_ID = '" + apptID + "';";
        Query.makeQuery(sqlstmt);
    }

    /**
     * Collects specific Appointment data from the database that matches the Customer ID passed to method and returns an Observable list.
     *   @param custID PVariable to hold Customer ID.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList<appointment> getAllByCustID(int custID) throws SQLException, Exception {
        ObservableList<appointment> list = FXCollections.observableArrayList();
        JDBC.getConnection();
        String sqlstmt = "SELECT * FROM appointments WHERE Customer_ID = '" + custID + "';";
        Query.makeQuery(sqlstmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactID = result.getInt("Contact_ID");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            appointmentResult = new appointment(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
            list.addAll(appointmentResult);
        }
        return list;
    }

    /**
     * Collects specific Appointment data from the database that matches the Customer ID passed to method and returns an Observable list.
     *   @param ID Variable to hold Customer ID.
     *   @throws Exception From FXMLLoader.
     */
    public static ObservableList getAllExcept(int ID) throws SQLException, Exception {
        ObservableList<appointment> list = FXCollections.observableArrayList();
        JDBC.getConnection();
        String sqlstmt = "SELECT * FROM appointments WHERE Appointment_ID NOT IN ('" + ID + "');";
        Query.makeQuery(sqlstmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactID = result.getInt("Contact_ID");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            appointmentResult = new appointment(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
            list.addAll(appointmentResult);
        }
        return list;
    }

}
