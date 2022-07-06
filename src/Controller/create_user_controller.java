package Controller;

import Database.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.LoginController.alertToDisplay;

/**
 * Controller class that provides control logic for the create user screen of the application.
 *
 * @author Zachary Deacon
 */

public class create_user_controller implements Initializable {
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
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }


    /**
     * Creates and saves a new User to the database.
     *   @param actionEvent Part search button action.
     *   @throws IOException From FXMLLoader.
     */
    public void makeUser(ActionEvent actionEvent) throws Exception {
        if(userNameText.getText().equals("") || passwordText.getText().equals("")){
            alertToDisplay(8);
        } else {
            UserDao.createUser(userNameText.getText(), passwordText.getText());

            Parent root = FXMLLoader.load(getClass().getResource("/view/log-in_form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Log-in Page");
            stage.setScene(scene);
            stage.show();
        }
    }


    /**
     * Closes the program.
     */
    public void exitProgram() {
        System.exit(0);
    }
}
