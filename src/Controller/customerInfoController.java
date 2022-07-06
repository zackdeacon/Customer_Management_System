package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Database.countryDao;
import Database.divisionDao;
import Model.appointment;
import Model.country;
import Model.customer;
import Model.division;
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
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static Controller.LoginController.alertToDisplay;
import static Controller.LoginController.activeUser;

/**
 * Controller class that provides control logic for the customer info screen of the application.
 *
 * @author Zachary Deacon
 */

public class customerInfoController implements Initializable {
    /**
     * Observable list of Country Objects.
     */
    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    /**
     * Observable list of Division Objects.
     */
    ObservableList<division> divisionOptions = FXCollections.observableArrayList();
    /**
     * The Name Text Field.
     */
    @FXML
    public TextField textName;
    /**
     * The ID Text Field.
     */
    @FXML
    public TextField textID;
    /**
     * The Address Text Field.
     */
    @FXML
    public TextField textAddress;
    /**
     * The Phone Number Text Field.
     */
    @FXML
    public TextField textPhone;
    /**
     * The Postal Code Text Field.
     */
    @FXML
    public TextField textPostalCode;
    /**
     * The Customer Table View.
     */
    @FXML
    public TableView customerTable;
    /**
     * The Customer Name Table Column.
     */
    @FXML
    public TableColumn customerName;
    /**
     * The Customer ID Table Column.
     */
    @FXML
    public TableColumn customerID;
    /**
     * The Customer Address Table Column.
     */
    @FXML
    public TableColumn customerAddress;
    /**
     * The Customer Postal Code Table Column.
     */
    @FXML
    public TableColumn customerPostalCode;
    /**
     * The Customer Phone Number Table Column.
     */
    @FXML
    public TableColumn customerPhone;
    /**
     * The Customer Division Table Column.
     */
    @FXML
    public TableColumn customerDivision;
    /**
     * The Customer Country Table Column.
     */
    @FXML
    public TableColumn customerCountry;
    /**
     * The User Label.
     */
    @FXML
    public Label userLabel;
    /**
     * The Appointment Warning Label.
     */
    @FXML
    public Label apptWarningLabel;
    /**
     * The Country Combo Box.
     */
    @FXML
    public ComboBox<country> updateCountry;
    /**
     * The Division Combo Box.
     */
    @FXML
    public ComboBox<division> updateDivision;
    /**
     * Customer search text field.
     */
    @FXML
    public TextField searchCustomer;
    /**
     * Variable to hold new ID.
     */
    public static int newID;

    /**
     * Observable List of Customers.
     */
    ObservableList<customer> customerList = FXCollections.observableArrayList();
    /**
     * Observable List of Appointments.
     */
    ObservableList<appointment> appointmentList = FXCollections.observableArrayList();
    /**
     * The Selected Customer.
     */
    customer selectedCustomer;
    /**
     * Variable to hold selected ID.
     */
    int selectedID;

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentList.clear();
        userLabel.setText(activeUser.getUserName());
        try {
            customerName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
            customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
            customerTable.setItems(CustomerDao.getAllCustomer(customerList));
            newID = customerList.get(customerList.size()-1).getCustomer_ID() + 1;
            appointmentDao.getAll(appointmentList);
            int count = 0;
            LocalTime range = LocalTime.now().plusMinutes(15);

            while(count < appointmentList.size()){
               // System.out.println("appt: " + appointmentList.get(count).getStart().toLocalTime());
                if(appointmentList.get(count).getStart().toLocalTime().isAfter(LocalTime.now()) && appointmentList.get(count).getStart().toLocalTime().isBefore(range)){
                    alertToDisplay(7);
                    apptWarningLabel.setText("Apppointment ID: " + appointmentList.get(count).getAppointmentID() + " is beginning at " + appointmentList.get(count).getStart().toLocalTime() + " on " + appointmentList.get(count).getStartDate());
                }
                count += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getSearchResults(ActionEvent actionEvent) throws Exception {
        String queue = searchCustomer.getText().toLowerCase();
        ObservableList<customer> theCustomers = searchCustomerName(queue);

        customerTable.setItems(theCustomers);
        searchCustomer.setText("");
    }

    private ObservableList<customer> searchCustomerName(String partialName) throws Exception {
        ObservableList<customer> selectedCustomers = FXCollections.observableArrayList();
        ObservableList<customer> allCustomers = FXCollections.observableArrayList();
        allCustomers = CustomerDao.getAllCustomer(allCustomers);
        for(customer selectedCustomer : allCustomers){
            if(selectedCustomer.getCustomer_Name().toLowerCase().contains(partialName)){
                selectedCustomers.add(selectedCustomer);
            } else if(String.valueOf(selectedCustomer.getCustomer_ID()).contains(partialName)){
                selectedCustomers.add(selectedCustomer);
            }
        }

        if(selectedCustomers.size() == 0){
            alertToDisplay(12);
            return allCustomers;
        }

        return selectedCustomers;
    };

    public void goToVipCustomers(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/vip_customer_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1225, 700);
        stage.setTitle("VIP Customers Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates the GUI field options with selected customers data for user to update.
     */
    public void updateCustomer() {
       selectedCustomer = (customer) customerTable.getSelectionModel().getSelectedItem();
           textName.setText(selectedCustomer.getCustomer_Name());
           textAddress.setText(selectedCustomer.getAddress());
           textPostalCode.setText(selectedCustomer.getPostal_Code());
           textPhone.setText(selectedCustomer.getPhone());
           selectedID = selectedCustomer.getCustomer_ID();
           textID.setText(String.valueOf(selectedID));
           try{
               updateCountry.getItems().clear();
               updateCountry.setItems(countryDao.getAllCountries(countryOptions));
               updateDivision.getItems().clear();
               updateDivision.setItems(divisionDao.getAllDivision(divisionOptions, selectedCustomer.getCountryID()));
               for(country s : updateCountry.getItems()){
                   if(selectedCustomer.getCountryID() == s.getCountryID()){
                       updateCountry.setValue(s);
                       break;
                   }
               }
               for(division d : updateDivision.getItems()){
                   if(selectedCustomer.getDivisionID() == d.getDivisionID()){
                       updateDivision.setValue(d);
                       break;
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
    }

    /**
     * Updates Division Combo Box based on the selected Country.
     */
    public void selectDivisionByCountry(ActionEvent actionEvent) throws IOException {
       int updatedCountry = updateCountry.getSelectionModel().getSelectedItem().getCountryID();
        updateDivision.getItems().clear();
        try {
            updateDivision.setItems(divisionDao.getAllDivision(divisionOptions, updatedCountry));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves updates made by the user for the selected customer to the Database.
     */
    public void completeUpdatedCustomer() {
        if(selectedCustomer == null){
            alertToDisplay(5);
        } else if(updateDivision.getSelectionModel().getSelectedItem() == null){
            alertToDisplay(6);
        } else {
            CustomerDao.updateCustomerSQL(selectedID, textName.getText(), textAddress.getText(), textPostalCode.getText(), textPhone.getText(), activeUser.getUserName(), updateDivision.getSelectionModel().getSelectedItem().getDivisionID());
            try {
                customerTable.getItems().clear();
                customerName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
                customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
                customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
                customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
                customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
                customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
                customerTable.setItems(CustomerDao.getAllCustomer(customerList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the selected customer from the database and then reloads the page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void deleteCustomer(ActionEvent actionEvent) throws IOException {
        try {
            int custID = selectedCustomer.getCustomer_ID();
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Delete Customer");
            confirmAlert.setHeaderText("You are about to delete " + selectedCustomer.getCustomer_Name() + ". Are you sure?");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    informAlert.setTitle("Deleted");
                    informAlert.setHeaderText(selectedCustomer.getCustomer_Name() + " has been deleted");
                    informAlert.show();
                    CustomerDao.deleteCustomer(custID);
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1150, 750);
            stage.setTitle("Customer Landing Page");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            alertToDisplay(11);
        }
    }

    /**
     * Navigates user to the reports page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void toReports(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 1000);
        stage.setTitle("Reports Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navaigates user to the Add Customer page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void goToAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/add_customer_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 500);
        stage.setTitle("Add Customer Page");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Navigates user to the Appointment Info page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void goToAppointments(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1225, 700);
        stage.setTitle("Appointments Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes the program.
     */
    public void exitProgram() {
        System.exit(0);
    }
}
