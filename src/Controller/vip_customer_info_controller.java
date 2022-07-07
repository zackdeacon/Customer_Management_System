package Controller;

import Database.CustomerDao;
import Database.countryDao;
import Database.divisionDao;
import Database.vip_customersDAO;
import Model.country;
import Model.customer;
import Model.division;
import Model.vip_customer;
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
import java.util.ResourceBundle;

import static Controller.LoginController.activeUser;
import static Controller.LoginController.alertToDisplay;

public class vip_customer_info_controller implements Initializable {

    /**
     * The Customer Name Text Field.
     */
    @FXML
    public TextField customerName;
    /**
     * The Customer Addres Text Field.
     */
    @FXML
    public TextField customerAddress;
    /**
     * The Customer Postal Code Text Field.
     */
    @FXML
    public TextField customerPostalCode;
    /**
     * The Customer Phone Text Field.
     */
    @FXML
    public TextField customerPhone;
    /**
     * The Customer Company Name Text Field.
     */
    @FXML
    public TextField customerCompanyName;
    /**
     * The Customer Country Combo Box.
     */
    @FXML
    public ComboBox<country> customerCountry;
    /**
     * The Customer Division Combo Box.
     */
    @FXML
    public ComboBox<division> customerDivision;
    /**
     * The VIP Customer Table View.
     */
    @FXML
    public TableView vipCustomerTable;
    /**
     * The Customer ID Table Column.
     */
    @FXML
    public TableColumn customerIDCol;
    /**
     * The Customer Name Table Column.
     */
    @FXML
    public TableColumn customerNameCol;
    /**
     * The Customer Address Table Column.
     */
    @FXML
    public TableColumn customerAddressCol;
    /**
     * The Customer Postal Code Table Column.
     */
    @FXML
    public TableColumn customerPostalCodeCol;
    /**
     * The Customer Phone Table Column.
     */
    @FXML
    public TableColumn customerPhoneCol;
    /**
     * The Customer Company Name Table Column.
     */
    @FXML
    public TableColumn customerCompanyNameCol;
    /**
     * The Customer Division Table Column.
     */
    @FXML
    public TableColumn customerDivisionCol;
    /**
     * The Customer Country Table Column.
     */
    @FXML
    public TableColumn customerCountryCol;
    /**
     * A list of Countries to use to populate Combo Box.
     */
    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    /**
     * A list of Divisions to use to populate Combo Box.
     */
    ObservableList<division> divisionOptions = FXCollections.observableArrayList();
    /**
     * Observable List of VIP Customers.
     */
    ObservableList<vip_customer> customerList = FXCollections.observableArrayList();

    vip_customer selectedCustomer;
    /**
     * Variable to hold selected ID.
     */
    int selectedID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerCountry.setItems(countryDao.getAllCountries(countryOptions));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
            customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customerCompanyNameCol.setCellValueFactory(new PropertyValueFactory<>("company"));
            customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
            customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
            vipCustomerTable.setItems(vip_customersDAO.getAllCustomer(customerList));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets Division Combo Box to display CDivision options based on selected Country.
     */
    public void countrySelected(){
        customerDivision.getItems().clear();
        int selectedCountry = customerCountry.getSelectionModel().getSelectedItem().getCountryID();
        try {
            customerDivision.setItems(divisionDao.getAllDivision(divisionOptions, selectedCountry));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createVIP(ActionEvent actionEvent) throws IOException{
        try{
            int selectedDivisionID = customerDivision.getSelectionModel().getSelectedItem().getDivisionID();
            String selectedName = customerName.getText();
            String selectedAddress = customerAddress.getText();
            String selectedPostal = customerPostalCode.getText();
            String selectedPhone = customerPhone.getText();
            String selectedCompany = customerCompanyName.getText();

            vip_customersDAO.createCustomerSQL(selectedDivisionID, selectedName, selectedAddress,selectedPostal, selectedPhone, activeUser.getUserName(),selectedCompany);
            Parent root = FXMLLoader.load(getClass().getResource("/view/vip_customer_form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1225, 1000);
            stage.setTitle("VIP Customers Page");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            alertToDisplay(8);
        }
    }

    public void updateVIP(){
        selectedCustomer = (vip_customer) vipCustomerTable.getSelectionModel().getSelectedItem();
        customerName.setText(selectedCustomer.getCustomer_Name());
        customerAddress.setText(selectedCustomer.getAddress());
        customerPostalCode.setText(selectedCustomer.getPostal_Code());
        customerPhone.setText(selectedCustomer.getPhone());
        customerCompanyName.setText(selectedCustomer.getCompany());
        selectedID = selectedCustomer.getVIP_ID();
        try{
            customerCountry.getItems().clear();
            customerCountry.setItems(countryDao.getAllCountries(countryOptions));
            customerDivision.getItems().clear();
            customerDivision.setItems(divisionDao.getAllDivision(divisionOptions, selectedCustomer.getCountryID()));
            for(country s : customerCountry.getItems()){
                if(selectedCustomer.getCountryID() == s.getCountryID()){
                    customerCountry.setValue(s);
                    break;
                }
            }
            for(division d : customerDivision.getItems()){
                if(selectedCustomer.getDivisionID() == d.getDivisionID()){
                    customerDivision.setValue(d);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves updates made by the user for the selected vip customer to the Database.
     */
    public void completeUpdatedVipCustomer() {
        if(selectedCustomer == null){
            alertToDisplay(5);
        } else if(customerDivision.getSelectionModel().getSelectedItem() == null){
            alertToDisplay(6);
        } else {
            vip_customersDAO.updateCustomerSQL(selectedID, customerName.getText(), customerAddress.getText(), customerPostalCode.getText(), customerPhone.getText(), activeUser.getUserName(), customerDivision.getSelectionModel().getSelectedItem().getDivisionID(), customerCompanyName.getText());
            try {
                vipCustomerTable.getItems().clear();
                customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
                customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
                customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
                customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
                customerCompanyNameCol.setCellValueFactory(new PropertyValueFactory<>("company"));
                customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
                customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
                vipCustomerTable.setItems(vip_customersDAO.getAllCustomer(customerList));
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
    public void deleteVIP(ActionEvent actionEvent) throws IOException {
        try {
            int custID = selectedCustomer.getVIP_ID();
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Delete Customer");
            confirmAlert.setHeaderText("You are about to delete " + selectedCustomer.getCustomer_Name() + ". Are you sure?");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    informAlert.setTitle("Deleted");
                    informAlert.setHeaderText(selectedCustomer.getCustomer_Name() + " has been deleted");
                    informAlert.show();
                    vip_customersDAO.deleteCustomer(custID);
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/view/vip_customer_form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1225, 1000);
            stage.setTitle("VIP Customer Page");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            alertToDisplay(13);
        }
    }

    /**
     * Navigates user to the Customers page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void goToCustomers(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1225, 1000);
        stage.setTitle("Customer Page");
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
