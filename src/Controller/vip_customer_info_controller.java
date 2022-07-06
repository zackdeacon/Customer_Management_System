package Controller;

import Database.countryDao;
import Database.divisionDao;
import Model.country;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerCountry.setItems(countryDao.getAllCountries(countryOptions));
            customerIDCol.setCellValueFactory(new PropertyValueFactory<>("VIP_customer_ID"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
            customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customerCompanyNameCol.setCellValueFactory(new PropertyValueFactory<>("company"));
            customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
            customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
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

        }catch(Exception e){
            alertToDisplay(8);
        }
    }

    public void updateVIP(){

    }

    public void deleteVIP() {

    }

    /**
     * Navigates user to the Customers page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void goToCustomers(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 1000);
        stage.setTitle("Reports Page");
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
