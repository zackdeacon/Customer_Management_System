package Controller;

import Database.CustomerDao;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static Controller.LoginController.activeUser;
import static Controller.LoginController.alertToDisplay;
import static Controller.customerInfoController.newID;

/**
 * Controller class that provides control logic for the add customer screen of the application.
 *
 * @author Zachary Deacon
 */

public class add_customer_controller implements Initializable {
    /**
     * A list of Countries to use to populate Combo Box.
     */
    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    /**
     * A list of Divisions to use to populate Combo Box.
     */
    ObservableList<division> divisionOptions = FXCollections.observableArrayList();

    /**
     * The label to show who is logged in.
     */
    @FXML
    public Label loggedIn;

    /**
     * The customer ID textfield.
     */
    @FXML
    public TextField customerID;

    /**
     * The country Combo Box.
     */
    @FXML
    public ComboBox<country> countryBox;

    /**
     * The Division Combo Box.
     */
    @FXML
    public ComboBox<division> divisionBox;

    /**
     * The Name textfield.
     */
    @FXML
    public TextField textName;

    /**
     * The Address textfield.
     */
    @FXML
    public TextField textAdd;

    /**
     * The Postal Code textfield.
     */
    @FXML
    public TextField textPost;

    /**
     * The Phone Number textfield.
     */
    @FXML
    public TextField textPhone;

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedIn.setText(activeUser.getUserName());
        customerID.setText(String.valueOf(newID));
        try {
            countryBox.setItems(countryDao.getAllCountries(countryOptions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets Division Combo Box to display CDivision options based on selected Country.
     */
    public void countrySelected(){
        divisionBox.getItems().clear();
        int selectedCountry = countryBox.getSelectionModel().getSelectedItem().getCountryID();
        try {
            divisionBox.setItems(divisionDao.getAllDivision(divisionOptions, selectedCountry));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a new user that is stored in the database.
     *
     * @param actionEvent Create User button.
     * @throws IOException From FXMLLoader.
     */
    public void createNewUser(ActionEvent actionEvent) throws IOException {
        try {
            int selectedCountryID = divisionBox.getSelectionModel().getSelectedItem().getCountryID();
            int selectedDivisionID = divisionBox.getSelectionModel().getSelectedItem().getDivisionID();
            int selectedCustomerID = Integer.parseInt(customerID.getText());
            String selectedName = textName.getText();
            String selectedAddress = textAdd.getText();
            String selectedPostal = textPost.getText();
            String selectedPhone = textPhone.getText();

            CustomerDao.createCustomerSQL(selectedCustomerID, selectedDivisionID, selectedName, selectedAddress, selectedPostal, selectedPhone, activeUser.getUserName());

            Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1150, 750);
            stage.setTitle("Customer Landing Page");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            alertToDisplay(8);
        }
    }

    /**
     * Loads Customer Info page.
     *
     * @param actionEvent Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    public void goBack(ActionEvent actionEvent)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes Program.
     */
    public void depart() {
        System.exit(0);
    }

}
