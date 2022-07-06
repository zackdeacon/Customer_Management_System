package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Database.countryDao;
import Database.reportsDao;
import Model.appointment;
import Model.contact;
import Model.country;
import Model.customer;
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
import java.time.Month;
import java.util.ResourceBundle;

/**
 * Controller class that provides control logic for the reports screen of the application.
 *
 * @author Zachary Deacon
 */

public class reports_controller implements Initializable {
    /**
     * The Type Combo Box.
     */
    @FXML
    public ComboBox<String> typeBox;
    /**
     * The Month Combo Box.
     */
    @FXML
    public ComboBox<Month> monthBox;
    /**
     * The Contact Combo Box.
     */
    @FXML
    public ComboBox<contact> contactBox;
    /**
     * The Country Combo Box.
     */
    @FXML
    public ComboBox<country> countryBox;
    /**
     * The Type Label.
     */
    @FXML
    public Label typeLabel;
    /**
     * The Number of Type Label.
     */
    @FXML
    public Label typeNum;
    /**
     * The Month Label.
     */
    @FXML
    public Label monthLabel;
    /**
     * The Number of Month Label.
     */
    @FXML
    public Label monthNum;
    /**
     * The Schedule Table View.
     */
    @FXML
    public TableView tableSched;
    /**
     * The Appointment ID Table Column.
     */
    @FXML
    public TableColumn apptID;
    /**
     * The Title Table Column.
     */
    @FXML
    public TableColumn Title;
    /**
     * The Type Table Column.
     */
    @FXML
    public TableColumn Type;
    /**
     * The Description Table Column.
     */
    @FXML
    public TableColumn Desc;
    /**
     * The Start Table Column.
     */
    @FXML
    public TableColumn start;
    /**
     * The End Table Column.
     */
    @FXML
    public TableColumn end;
    /**
     * The Customer ID Table Column.
     */
    @FXML
    public TableColumn custID;
    /**
     * The Customer Label.
     */
    @FXML
    public Label customerLabel;
    /**
     * The Customer Number Label.
     */
    @FXML
    public Label customerNum;
    /**
     * The Schedule Label.
     */
    @FXML
    public Label contactSchedLabel;

    /**
     * Observable List of Contacts.
     */
    ObservableList<contact> contList = FXCollections.observableArrayList();
    /**
     * Observable List of Countries.
     */
    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    /**
     * Observable List of Customers.
     */
    ObservableList<customer> customerList = FXCollections.observableArrayList();
    /**
     * Observable List of Types.
     */
    ObservableList<String> typeList = FXCollections.observableArrayList();
    /**
     * Observable List of Appointments.
     */
    ObservableList<appointment> apptList = FXCollections.observableArrayList();
    /**
     * Observable List of Months.
     */
    ObservableList<Month> monthList = FXCollections.observableArrayList();
    /**
     * Observable List of Appointments.
     */
    ObservableList<appointment> apptByContactList = FXCollections.observableArrayList();

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeList.add("Work");
        typeList.add("Pleasure");
        monthList.add(Month.JANUARY);
        monthList.add(Month.FEBRUARY);
        monthList.add(Month.MARCH);
        monthList.add(Month.APRIL);
        monthList.add(Month.MAY);
        monthList.add(Month.JUNE);
        monthList.add(Month.JULY);
        monthList.add(Month.AUGUST);
        monthList.add(Month.SEPTEMBER);
        monthList.add(Month.OCTOBER);
        monthList.add(Month.NOVEMBER);
        monthList.add(Month.DECEMBER);
        try{
            contactBox.getItems().clear();
            contactBox.setItems(appointmentDao.getAllContacts(contList));
            countryBox.getItems().clear();
            countryBox.setItems(countryDao.getAllCountries(countryOptions));
            typeBox.setItems(typeList);
            typeBox.setValue("Work");
            monthBox.setItems(monthList);
            monthBox.setValue(monthList.get(0));
            contactBox.setValue(contList.get(0));
            countryBox.setValue(countryOptions.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Computes the number of Appointments by a certain Type.
     * @param actionEvent Part search button action.
     * @throws IOException From FXMLLoader.
     */
    public void appointmentByType(ActionEvent actionEvent) throws Exception {
        String type = typeBox.getSelectionModel().getSelectedItem();
        ObservableList<appointment> apptType = reportsDao.getApptByType(type);
        int count = 0;
        Month selectedMonth = monthBox.getSelectionModel().getSelectedItem();
        for(appointment a : apptType) {
            Month month = a.getStartDate().getMonth();
            if(month == selectedMonth) {
                count+=1;
            }
        }
        typeLabel.setText("Total " + type + " Appointments in the month of: " + selectedMonth);
        typeNum.setText(String.valueOf(count));
    }

    /**
     * Set's the Schedule table view with data from selected Contact.
     * @throws IOException From FXMLLoader.
     */
    public void schedByContact() throws Exception {
        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));
        custID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
        String ContactName = contactBox.getSelectionModel().getSelectedItem().getName();
        apptByContactList.clear();
        tableSched.setItems(reportsDao.getApptByContact(contactID, apptByContactList));

        contactSchedLabel.setText("See " + ContactName + "'s schedule below:");
    }

    /**
     * Sends the customer data by selected Country.
     * @throws IOException From FXMLLoader.
     */
    public void customerByCountry() throws Exception {
        String country = countryBox.getSelectionModel().getSelectedItem().getCountryName();
        int countryID = countryBox.getSelectionModel().getSelectedItem().getCountryID();
        customerList.clear();
        CustomerDao.getAllCustomer(customerList);
        int count = 0;
        for(customer c : customerList){
            if(c.getCountryID() == countryID){
                count +=1;
            }
        }
        customerLabel.setText("The number of Customers in " + country + " are:");
        customerNum.setText(String.valueOf(count));
    }

    /**
     * Navigates user to the Customer Info page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void goBack(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes the Application.
     */
    public void close() {
        System.exit(0);
    }



}
