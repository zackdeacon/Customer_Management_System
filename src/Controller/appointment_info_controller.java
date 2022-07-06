package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Model.*;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import static Controller.LoginController.activeUser;
import static Controller.LoginController.alertToDisplay;

/**
 * Controller class that provides control logic for the appointment info screen of the application.
 *
 * @author Zachary Deacon
 */

public class appointment_info_controller implements Initializable {
    /**
     * The appointment Table View .
     */
    @FXML
    public TableView appointmentTable;
    /**
     * The Appointment ID Table Column.
     */
    @FXML
    public TableColumn appIDCol;
    /**
     * The Title Table Column.
     */
    @FXML
    public TableColumn titleCol;
    /**
     * The Description Table Column.
     */
    @FXML
    public TableColumn descCol;
    /**
     * The Location Table Column.
     */
    @FXML
    public TableColumn locCol;
    /**
     * The Contact Table Column.
     */
    @FXML
    public TableColumn contactCol;
    /**
     * The Type Table Column.
     */
    @FXML
    public TableColumn typeCol;
    /**
     * The Start Table Column.
     */
    @FXML
    public TableColumn startCol;
    /**
     * The End Table Column.
     */
    @FXML
    public TableColumn endCol;
    /**
     * The Customer Table Column.
     */
    @FXML
    public TableColumn custCol;
    /**
     * The User ID Table Column.
     */
    @FXML
    public TableColumn userCol;
    /**
     * The Selected User Label.
     */
    @FXML
    public Label userSelectedLabel;
    /**
     * The Title Text Field.
     */
    @FXML
    public TextField titleText;
    /**
     * The Description Text Field.
     */
    @FXML
    public TextField descText;
    /**
     * The Location Text Field.
     */
    @FXML
    public TextField locText;
    /**
     * The Appointment ID Text Field.
     */
    @FXML
    public TextField apptIDText;
    /**
     * The Type Combo Box.
     */
    @FXML
    public ComboBox<String> typeText;
    /**
     * The Contact Combo Box.
     */
    @FXML
    public ComboBox<contact> contactBox;
    /**
     * The Customer Combo Box.
     */
    @FXML
    public ComboBox<customer> customerBox;
    /**
     * The User Combo Box.
     */
    @FXML
    public ComboBox<user> userBox;
    /**
     * The Start Combo Box.
     */
    @FXML
    public ComboBox<LocalTime> startCombo;
    /**
     * The End Combo Box.
     */
    @FXML
    public ComboBox<LocalTime> endCombo;
    /**
     * The Monthly view Radio Button.
     */
    @FXML
    public RadioButton monthlyRadio;
    /**
     * The Weekly view Radio Button.
     */
    @FXML
    public RadioButton weeklyRadio;
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
     * The search appointments Text Field.
     */
    @FXML
    public TextField searchAppointments;

    /**
     * An observable list of Appointments.
     */
    ObservableList<appointment> apptList = FXCollections.observableArrayList();
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
     * An observable list of Type selections.
     */
    ObservableList<String> typeList = FXCollections.observableArrayList();
    /**
     * A Local Date Time variable to hold current time and date
     */
    LocalDateTime today = LocalDateTime.now();
    /**
     * A Local Date Time variable to hold one week ahead.
     */
    LocalDateTime lastDayWeek = LocalDateTime.now().plusDays(7);
    /**
     * A Local Date Time variable to hold one month ahead.
     */
    LocalDateTime lastDayMonth = LocalDateTime.now().plusDays(30);
    /**
     * Current selected Appointment.
     */
    appointment selectedAppointment;
    /**
     * Variable to hold Customer ID.
     */
    int custID;
    /**
     * Variable to hold Contact ID.
     */
    int contID;
    /**
     * Variable to hold User ID.
     */
    int userID;
    /**
     * Variable to hold beginning of the day.
     */
    LocalTime beginOfDay = LocalTime.of(8,0);
    /**
     * Variable to hold end of the day.
     */
    LocalTime endOfDay = LocalTime.of(22,0);
    /**
     * Variable to hold beginning of the day in Eastern time.
     */
    LocalDateTime beginToEST = timezones.localToEST(LocalDate.now(), beginOfDay);
    /**
     * Variable to hold end of the day in Eastern time.
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
        userSelectedLabel.setText(activeUser.getUserName());
        weeklyRadio.setSelected(true);
        weeklyView();
        typeList.add("Work");
        typeList.add("Pleasure");
    }

    /**
     * When selected, loads the Table View with Appointment data for the next month.
     */
    public void monthlyView() {
        weeklyRadio.setSelected(false);
        monthlyRadio.setSelected(true);
        appointmentTable.getItems().clear();
        appIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        try {
            appointmentTable.setItems(appointmentDao.getAllAppt(apptList, today, lastDayMonth));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When selected, loads the Table View with Appointment data for the next week.
     */
    public void weeklyView() {
        monthlyRadio.setSelected(false);
        weeklyRadio.setSelected(true);
        appointmentTable.getItems().clear();
        appIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        try {
            appointmentTable.setItems(appointmentDao.getAllAppt(apptList, today, lastDayWeek));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSearchResults(ActionEvent actionEvent) throws Exception {
        String queue = searchAppointments.getText().toLowerCase();
        ObservableList<appointment> theAppointments = searchAppointmentName(queue);

        appointmentTable.setItems(theAppointments);
        searchAppointments.setText("");
    }

    private ObservableList<appointment> searchAppointmentName(String partialName) throws Exception {
        ObservableList<appointment> selectedAppointments = FXCollections.observableArrayList();
        ObservableList<appointment> allAppointments = FXCollections.observableArrayList();
        allAppointments = appointmentDao.getAll(allAppointments);
        for(appointment selectedAppointment : allAppointments){
            if(selectedAppointment.getTitle().toLowerCase().contains(partialName)){
                selectedAppointments.add(selectedAppointment);
            } else if(String.valueOf(selectedAppointment.getAppointmentID()).contains(partialName)){
                selectedAppointments.add(selectedAppointment);
            }
        }

        if(selectedAppointments.size() == 0){
            alertToDisplay(12);
            weeklyRadio.setSelected(false);
            monthlyRadio.setSelected(false);
            return allAppointments;
        }
        weeklyRadio.setSelected(false);
        monthlyRadio.setSelected(false);
        return selectedAppointments;
    };

    /**
     * takes data from selected Appointment and loads it into the GUI for user to maipulate.
     * Lambda expression used to iterate through selected data and set combo boxes. This reduces code for efficiency
     * @throws Exception From FXMLLoader.
     */
    public void setUpdateAppointment() throws Exception {
        startCombo.getItems().clear();
        selectedAppointment = (appointment) appointmentTable.getSelectionModel().getSelectedItem();
        custID = selectedAppointment.getCustomerID();
        contID = selectedAppointment.getContactID();
        userID = selectedAppointment.getUserID();
        apptIDText.setText(String.valueOf(selectedAppointment.getAppointmentID()));
        titleText.setText(selectedAppointment.getTitle());
        descText.setText(selectedAppointment.getDescription());
        locText.setText(selectedAppointment.getLocation());
        if(selectedAppointment.getType().equals("Work")){
            typeText.setValue("Work");
        } else if(selectedAppointment.getType().equals("Pleasure")){
            typeText.setValue("Pleasure");
        }


        LocalTime beginTime = beginToEST.toLocalTime();
        LocalTime endingTime = endToEST.toLocalTime();
        while(beginTime.isBefore(endingTime)){
            startCombo.getItems().add(beginTime);
            beginTime = beginTime.plusHours(1);
        }

        try{
            startDate.setValue(selectedAppointment.getStartDate());
            endDate.setValue(selectedAppointment.getEndDate());
            startCombo.setValue(selectedAppointment.getStartTime());
            endCombo.setValue(selectedAppointment.getEndTime());
            contactBox.getItems().clear();
            contactBox.setItems(appointmentDao.getAllContacts(contList));
            userBox.getItems().clear();
            userBox.setItems(appointmentDao.getAllUser(userList));
            customerBox.getItems().clear();
            customerBox.setItems(CustomerDao.getAllCustomer(custList));
            typeText.setItems(typeList);

            /** Lambda Expression
             */
            contactBox.getItems().forEach( (c) -> {
                if (selectedAppointment.getContactID() == c.getContactID()) {
                    contactBox.setValue(c);
                }
            });

            /** Lambda Expression
             */
            userBox.getItems().forEach( (u) -> {
                if(selectedAppointment.getUserID() == u.getUserID()){
                    userBox.setValue(u);
                }
            });

            /** Lambda Expression
             */
            customerBox.getItems().forEach( (cu) -> {
                if(selectedAppointment.getCustomerID() == cu.getCustomer_ID()){
                    customerBox.setValue(cu);
                }
            });


        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the end time Combo Box when the based on the selected start time.
     */
    public void updateEndTime(){
        endCombo.getItems().clear();
        LocalTime selectedStart = startCombo.getSelectionModel().getSelectedItem();
        LocalTime initialLoad = selectedStart.plusHours(1);
        selectedStart = selectedStart.plusHours(1);
        while(selectedStart.isBefore(endToEST.toLocalTime().plusHours(1))){
            endCombo.getItems().add(selectedStart);
            selectedStart = selectedStart.plusHours(1);
        }
        endCombo.setValue(initialLoad);
    }

    /**
     * Deletes the selected Appointment by Appointment ID.
     * Lambda expression used to pass button response to method and choose action. This allows for simplified code
     * @param actionEvent Part search button action.
     *  @throws IOException From FXMLLoader.
     */
    public void deleteAppt(ActionEvent actionEvent) throws IOException {
        try {
            int appID = selectedAppointment.getAppointmentID();
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Delete Appointment ID: " + selectedAppointment.getAppointmentID());
            confirmAlert.setHeaderText("You are about to delete the " + selectedAppointment.getType() + " appointment titled: " + selectedAppointment.getTitle() + ". Are you sure?");
            /** Lambda Expression
             */
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    informAlert.setTitle("Deleted ID: " + selectedAppointment.getAppointmentID());
                    informAlert.setHeaderText("The " + selectedAppointment.getType() + " appointment titled: " + selectedAppointment.getTitle() + " has been deleted");
                    informAlert.show();
                    appointmentDao.deleteAppointment(appID);
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1300, 950);
            stage.setTitle("Appointment Info Page");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            alertToDisplay(11);
        }
    }

    /**
     * Navigates user to the Create Appointment form.
     * @param actionEvent Part search button action.
     *  @throws IOException From FXMLLoader.
     */
    public void createAppt(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/create_appointment_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Create Appointment Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Saves the updated Appointment to the database and reloads the page.
     * @param actionEvent Part search button action.
     *  @throws IOException From FXMLLoader.
     */
    public void updateAppt(ActionEvent actionEvent) throws Exception {
        if(appointmentTable.getSelectionModel().getSelectedItem() == null){
            alertToDisplay(10);
        } else {
            int appID = Integer.parseInt(apptIDText.getText());
            String title = titleText.getText();
            String description = descText.getText();
            String location = locText.getText();
            String type = typeText.getSelectionModel().getSelectedItem();
            LocalDateTime start = LocalDateTime.of(startDate.getValue(), startCombo.getValue());
            LocalDateTime end = LocalDateTime.of(endDate.getValue(), endCombo.getValue());
            String userName = activeUser.getUserName();
            int custID = customerBox.getSelectionModel().getSelectedItem().getCustomer_ID();
            int userID = userBox.getSelectionModel().getSelectedItem().getUserID();
            int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
            boolean cleared = false;
            ObservableList<appointment> overlap = appointmentDao.getAllExcept(selectedAppointment.getAppointmentID());
            for (int i = 0; i < overlap.size(); i++) {
                if ((start.isAfter(overlap.get(i).getStart()) || start.isEqual(overlap.get(i).getStart())) && start.isBefore(overlap.get(i).getEnd())) {
                    alertToDisplay(9);
                    cleared = false;
                    break;
                } else if (end.isAfter(overlap.get(i).getStart()) && (end.isBefore(overlap.get(i).getEnd()) || end.isEqual(overlap.get(i).getEnd()))) {
                    alertToDisplay(9);
                    cleared = false;
                    break;
                } else if ((start.isBefore(overlap.get(i).getStart()) || start.isEqual(overlap.get(i).getStart())) && (end.isAfter(overlap.get(i).getEnd()) || end.isEqual(overlap.get(i).getEnd()))) {
                    alertToDisplay(9);
                    cleared = false;
                    break;
                }
                cleared = true;
            }
            if (cleared == true) {
                try {
                    appointmentDao.updateAppointmentSQL(appID, title, description, location, type, start, end, userName, custID, userID, contactID);
                    Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
                    Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1300, 950);
                    stage.setTitle("Appointment Info Page");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Closes the application.
     */
    public void closeApplication() {
        System.exit(0);
    }

    /**
     * Navigates the user back to the Customer Info page.
     * @param actionEvent Part search button action.
     *  @throws IOException From FXMLLoader.
     */
    public void goBack(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }
}
