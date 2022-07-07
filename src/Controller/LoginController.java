package Controller;

import Database.UserDao;
import Model.user;
import Utility.filewriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class that provides control logic for the login screen of the application.
 *
 * @author Zachary Deacon
 */

public class LoginController implements Initializable{
    /**
     * The Location Alert Label.
     */
    @FXML
    public Label locationAlertLabel;
    /**
     * The Welcome Label.
     */
    @FXML
    public Label welcomeLabel;
    /**
     * The User Name Label.
     */
    @FXML
    public Label userNameLabel;
    /**
     * The Password Label.
     */
    @FXML
    public Label passwordLabel;
    /**
     * The Location Label.
     */
    @FXML
    public Label locationLabel;
    /**
     * The Login Button.
     */
    @FXML
    public Button loginButton;
    /**
     * The Create User Button.
     */
    @FXML
    public Button createButton;
    /**
     * The Exit Button.
     */
    @FXML
    public Button exitButton;
    /**
     * The User Name Text Field.
     */
    @FXML
    public TextField userNameText;
    /**
     * The Password Text Field.
     */
    @FXML
    public TextField passwordText;
    /**
     * Variable to hold Active User.
     */
    static user activeUser;
    /**
     * Variable to hold today's Date.
     */
    LocalDate date = LocalDate.now();
    /**
     * Variable to hold today's current Time.
     */
    LocalTime time = LocalTime.now();

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
    }

    /**
     * Set's the language based on user's computer settings and location.
     */
    public void setLanguage() {
      // Locale.setDefault(new Locale("fr", "FR"));
        try{
           ResourceBundle rb = ResourceBundle.getBundle("translator", Locale.getDefault());
        welcomeLabel.setText(rb.getString("Welcome!") );
        userNameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        locationLabel.setText(rb.getString("Location"));
        loginButton.setText(rb.getString("Log-in"));
        exitButton.setText(rb.getString("Exit"));
        createButton.setText(rb.getString("Create"));
        locationAlertLabel.setText(Locale.getDefault().getDisplayCountry() + "; " + TimeZone.getDefault().getDisplayName());

    } catch (Exception e){
        locationAlertLabel.setText(Locale.getDefault().getDisplayCountry() + "; " + TimeZone.getDefault().getDisplayName());
    }
   }

    /**
     * Validates user log in credentials and then loads Customer Info page.
     */
   public void loginAttempt(ActionEvent actionEvent) throws IOException {
        String enteredName = userNameText.getText().toLowerCase();
        String enteredPassword = passwordText.getText();
       try{
          activeUser = UserDao.getUser(enteredName);
           if(activeUser == null && Locale.getDefault().getDisplayCountry().equals("France")) {
               alertToDisplay(1);
           } else if(activeUser == null && Locale.getDefault().getDisplayCountry().equals("United States")){
               alertToDisplay(2);
           }
            if(activeUser.getPassword().equals(enteredPassword)){
                filewriter.loginToFile(enteredName, date, time, "succeeded");
                Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1150, 750);
                stage.setTitle("Customer Landing Page");
                stage.setScene(scene);
                stage.show();
            } else {
                filewriter.loginToFile(enteredName, date, time, "failed");
                if(Locale.getDefault().getDisplayCountry().equals("France")) {
                    alertToDisplay(3);
                } else if(Locale.getDefault().getDisplayCountry().equals("United States")){
                    alertToDisplay(4);
                }
            }
       } catch(Exception ex){
           Logger.getLogger(customerInfoController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

    /**
     * Closes Program.
     */
    public void exitProgram() {
        System.exit(0);
    }

    /**
     * Navigates user to the Create User page.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void createUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/create_user_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Create User Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * defines different error handling alerts to be used throughout the program.
     *   @param alertNum number that is passed to switch case to determine which alert to give.
     */
    public static void alertToDisplay(int alertNum){
        try{
            ResourceBundle rb = ResourceBundle.getBundle("translator", Locale.getDefault());

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

        switch(alertNum){
            case 1:
                errorAlert.setTitle(rb.getString("E"));
                errorAlert.setHeaderText(rb.getString("Fail"));
                errorAlert.setContentText(rb.getString("Error"));
                errorAlert.showAndWait();
                break;
            case 2:
                errorAlert.setTitle(rb.getString("head"));
                errorAlert.setHeaderText(rb.getString("LI"));
                errorAlert.setContentText(rb.getString("body"));
                errorAlert.showAndWait();
                break;
            case 3:
                errorAlert.setTitle(rb.getString("E"));
                errorAlert.setHeaderText(rb.getString("Fail"));
                errorAlert.setContentText(rb.getString("wrongPass"));
                errorAlert.showAndWait();
                break;
            case 4:
                errorAlert.setTitle(rb.getString("head"));
                errorAlert.setHeaderText(rb.getString("LI"));
                errorAlert.setContentText(rb.getString("password"));
                errorAlert.showAndWait();
                break;
            case 5:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No Customer Selected");
                errorAlert.setContentText("Please select a customer from the list at the left to continue");
                errorAlert.showAndWait();
                break;
            case 6:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("No Division");
                errorAlert.setContentText("Please select a division for this customer in order to continue");
                errorAlert.showAndWait();
                break;
            case 7:
                infoAlert.setTitle("Alert!");
                infoAlert.setHeaderText("Appointment happening Soon");
                infoAlert.setContentText("Please be aware that you have an appointment happening in less than 15 minutes");
                infoAlert.showAndWait();
                break;
            case 8:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Incomplete information");
                errorAlert.setContentText("Please enter information in all text fields");
                errorAlert.showAndWait();
                break;
            case 9:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Overlapping Appointments");
                errorAlert.setContentText("This appointment overlaps with another appointment on this customer's schedule");
                errorAlert.showAndWait();
                break;
            case 10:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Nothing Selected!");
                errorAlert.setContentText("Please select an appointment to update!");
                errorAlert.showAndWait();
                break;
            case 11:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Nothing Selected!");
                errorAlert.setContentText("Please select an appointment to delete!");
                errorAlert.showAndWait();
                break;
            case 12:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Not a valid search!");
                errorAlert.setContentText("Please enter a name or ID to search for!");
                errorAlert.showAndWait();
                break;
            case 13:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Nothing Selected!");
                errorAlert.setContentText("Please select a customer to delete!");
                errorAlert.showAndWait();
                break;
        }
        } catch(Exception e){
            System.out.println("wasteful");
        }
    }

}
