package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Model.appointment;
import Model.contact;
import Model.customer;
import Model.user;
import Utility.timezones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static Controller.LoginController.activeUser;
import static Controller.LoginController.alertToDisplay;

/**
 * Controller class that provides control logic for the create appointment screen of the application.
 *
 * @author Zachary Deacon
 */

public class create_appointment_controller implements Initializable {
    /**
     * The Active User Label.
     */
    @FXML
    public Label activeUserLabel;
    /**
     * The Appointment ID Text Field.
     */
    @FXML
    public TextField textApptID;
    /**
     * The Title Text Field.
     */
    @FXML
    public TextField textTitle;
    /**
     * The Description Text Field.
     */
    @FXML
    public TextField textDesc;
    /**
     * The Location Text Field.
     */
    @FXML
    public TextField textLoc;
    /**
     * The Type Text Field.
     */
    @FXML
    public ComboBox textType;
    /**
     * The Start Date Picker.
     */
    @FXML
    public DatePicker startDate;
    /**
     * The End Date Picker.
     */
    @FXML
    public DatePicker endDate;
    /**
     * The Contact Combo Box.
     */
    @FXML
    public ComboBox<contact> contactBox;
    /**
     * The Customer Combo Box.
     */
    @FXML
    public ComboBox<customer> custBox;
    /**
     * The User Combo Box.
     */
    @FXML
    public ComboBox<user> userBox;
    /**
     * The Start Time Combo Box.
     */
    @FXML
    public ComboBox<LocalTime> startTime;
    /**
     * The End Time Combo Box.
     */
    @FXML
    public ComboBox<LocalTime> endTime;
    /**
     * An observable list of Contacts.
     */
    ObservableList<contact> contList = FXCollections.observableArrayList();
    /**
     * An observable list of Customers.
     */
    ObservableList<customer> custList = FXCollections.observableArrayList();
    /**
     * An observable list of Users.
     */
    ObservableList<user> userList = FXCollections.observableArrayList();
    /**
     * An observable list of Appointments.
     */
    ObservableList<appointment> apptList = FXCollections.observableArrayList();
    /**
     * An observable list of Types.
     */
    ObservableList<String> typeList = FXCollections.observableArrayList();
    /**
     * Variable to hold new Appointment ID.
     */
    public static int newAppID;
    /**
     * Variable to hold beginning of day time.
     */
    LocalTime beginOfDay = LocalTime.of(8,0);
    /**
     * Variable to hold end of day time.
     */
    LocalTime endOfDay = LocalTime.of(22,0);
    /**
     * Variable to hold beginning of day in Eastern time.
     */
    LocalDateTime beginToEST = timezones.localToEST(LocalDate.now(), beginOfDay);
    /**
     * Variable to hold end of day in Eastern Time.
     */
    LocalDateTime endToEST = timezones.localToEST(LocalDate.now(), endOfDay);
    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activeUserLabel.setText(activeUser.getUserName());
        typeList.add("Work");
        typeList.add("Pleasure");
        LocalTime beginTime = beginToEST.toLocalTime();
        LocalTime endingTime = endToEST.toLocalTime();
        while(beginTime.isBefore(endingTime)){
            startTime.getItems().add(beginTime);
            beginTime = beginTime.plusHours(1);
        }
        textType.setItems(typeList);
        textType.setValue(typeList.get(0));
        try {
        contactBox.getItems().clear();
        contactBox.setItems(appointmentDao.getAllContacts(contList));
        contactBox.setValue(contList.get(0));
        userBox.getItems().clear();
        userBox.setItems(appointmentDao.getAllUser(userList));
        userBox.setValue(userList.get(0));
        custBox.getItems().clear();
        custBox.setItems(CustomerDao.getAllCustomer(custList));
        custBox.setValue(custList.get(0));
        appointmentDao.getAll(apptList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now().plusDays(1));
        startTime.setValue(beginToEST.toLocalTime());
        if(apptList.size() != 0) {
            newAppID = apptList.get(apptList.size() - 1).getAppointmentID() + 1;
        } else {
            newAppID = 1;
        }
        textApptID.setText(String.valueOf(newAppID));
        setEndBox();
    }
    /**
     * Updates the end time Combo Box when the based on the selected start time.
     */
    public void setEndBox() {
        endTime.getItems().clear();
        LocalTime selectedStart = startTime.getSelectionModel().getSelectedItem();
        LocalTime initialLoad = selectedStart.plusHours(1);
        selectedStart = selectedStart.plusHours(1);
        while(selectedStart.isBefore(endToEST.toLocalTime().plusHours(1))){
            endTime.getItems().add(selectedStart);
            selectedStart = selectedStart.plusHours(1);
        }
        endTime.setValue(initialLoad);
    }

    /**
     * Creates and saves a new Appointmnet to the database.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void createAppt(ActionEvent actionEvent) throws Exception {
        String title = textTitle.getText();
        String description = textDesc.getText();
        String location = textLoc.getText();
        String type = textType.getSelectionModel().getSelectedItem().toString();
        LocalDateTime start = LocalDateTime.of(startDate.getValue(), startTime.getValue());
        LocalDateTime end = LocalDateTime.of(endDate.getValue(), endTime.getValue());
        String user = activeUser.getUserName();
        int customerID = custBox.getSelectionModel().getSelectedItem().getCustomer_ID();
        int userID = userBox.getSelectionModel().getSelectedItem().getUserID();
        int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
        boolean cleared = true;
        if(title.equals("") || description.equals("") || location.equals("")){
            alertToDisplay(8);
        } else {
            ObservableList<appointment> overlap = appointmentDao.getAllByCustID(customerID);
            for(int i =0; i < overlap.size(); i++){
                if( (start.isAfter(overlap.get(i).getStart())||start.isEqual(overlap.get(i).getStart())) && start.isBefore(overlap.get(i).getEnd())){
                    alertToDisplay(9);
                    cleared = false;
                    break;
                } else if(end.isAfter(overlap.get(i).getStart())&&(end.isBefore(overlap.get(i).getEnd())||end.isEqual(overlap.get(i).getEnd()))){
                    alertToDisplay(9);
                    cleared = false;
                    break;
                } else if((start.isBefore(overlap.get(i).getStart())||start.isEqual(overlap.get(i).getStart()))&&(end.isAfter(overlap.get(i).getEnd())||end.isEqual(overlap.get(i).getEnd()))){
                    alertToDisplay(9);
                    cleared = false;
                    break;
                }
                cleared = true;
            }
            if(cleared == true) {
                appointmentDao.insertAppointment(title, description, location, type, start, end, user, customerID, userID, contactID);

                Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1300, 950);
                stage.setTitle("Appointment Info Page");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /**
     * Navigates user to the Appointment Info form.
     * @param actionEvent Part search button action.
     *  @throws IOException From FXMLLoader.
     */
    public void goBackButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 950);
        stage.setTitle("Appointment Info Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes the program.
     */
    public void toExit() {
        System.exit(0);
    }
}
