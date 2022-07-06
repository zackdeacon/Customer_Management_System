package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Models an appointment.
 *
 * @author Zachary Deacon
 *
 */

public class appointment {

    /**
     * The ID for the appointment
     */
    private int appointmentID;
    /**
     * The Title for the appointment
     */
    private String title;
    /**
     * The description for the appointment
     */
    private String description;
    /**
     * The location for the appointment
     */
    private String location;
    /**
     * The Type for the appointment
     */
    private String type;
    /**
     * The start time for the appointment
     */
    private LocalDateTime start;
    /**
     * The end time for the appointment
     */
    private LocalDateTime end;
    /**
     * The contact ID for the appointment
     */
    private int contactID;
    /**
     * The customer ID for the appointment
     */
    private int customerID;
    /**
     * The user ID for the appointment
     */
    private int userID;


    /**
     * Constructor for a new instance of an appointment
     *
     * @param appointmentID the ID for the appointment
     * @param title the title of the appointment
     * @param description the description of the appointment
     * @param location the location of the appointment
     * @param type the type of appointment
     * @param start the start time of the appointment
     * @param end the end time for the appointment
     * @param contactID the contact ID attached to the appointment
     * @param customerID the customer ID attached to the appointment
     * @param userID the user ID attached to the appointment
     */
    public appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int contactID, int customerID, int userID){
        this.appointmentID = appointmentID;
        this.title=title;
        this.description=description;
        this.location=location;
        this.type=type;
        this.start=start;
        this.end=end;
        this.contactID=contactID;
        this.customerID=customerID;
        this.userID=userID;
    }

    /**
     * The getter for the appointment id
     *
     * @return appointmentID of the appointment
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * The setter for the appointment id
     *
     * @param appointmentID The id of the appointment
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * The getter for the appointment title
     *
     * @return title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * The setter for the appointment title
     *
     * @param title The title of the appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The getter for the appointment description
     *
     * @return description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * The setter for the appointment description
     *
     * @param description The description of the appointment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The getter for the appointment location
     *
     * @return location of the appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * The setter for the appointment location
     *
     * @param location The location of the appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * The getter for the appointment type
     *
     * @return type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * The setter for the appointment type
     *
     * @param type The type of the appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The getter for the appointment start date/time
     *
     * @return start of the appointment
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * The setter for the appointment start
     *
     * @param start The star of the appointment
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * The getter for the appointment end date/time
     *
     * @return end of the appointment
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * The setter for the appointment end
     *
     * @param end The end of the appointment
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * The getter for the appointment contact id
     *
     * @return contactID of the appointment
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * The setter for the appointment contact id
     *
     * @param contactID The contact id of the appointment
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * The getter for the appointment customer id
     *
     * @return customerID of the appointment
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * The setter for the appointment customer id
     *
     * @param customerID The customer id of the appointment
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The getter for the appointment user id
     *
     * @return userID of the appointment
     */
    public int getUserID() {
        return userID;
    }

    /**
     * The setter for the appointment user id
     *
     * @param userID The user id of the appointment
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * The getter for the appointment start date
     *
     * @return start date of the appointment
     */
    public LocalDate getStartDate() {
        return start.toLocalDate();
    }

    /**
     * The getter for the appointment start time
     *
     * @return start time of the appointment
     */
    public LocalTime getStartTime() {
        return start.toLocalTime();
    }

    /**
     * The getter for the appointment end time
     *
     * @return end time of the appointment
     */
    public LocalTime getEndTime() {
        return end.toLocalTime();
    }

    /**
     * The getter for the appointment end date
     *
     * @return end date of the appointment
     */
    public LocalDate getEndDate() {
        return end.toLocalDate();
    }
}
