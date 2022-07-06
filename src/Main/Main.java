package Main;

import Database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * JavaDoc files stored in src folder
 *
 * See below for FUTURE ENHANCEMENT
 *
 * The scheduling management system which allows one to log in, create users, create/update/delete customers and appointments
 * and manage interactions between those classes
 *
 * A feature to consider adding for future iterations of this project could be allowing for better GUI representation
 * for the schedules/calendars along with a mobile version. Also, you could add a messaging feature to allow people to remind others of cancellations
 * or upcoming appointments and to interact virtually outside of the appointments.
 *
 * @author Zachary Deacon
 *
 */

public class Main extends Application {

    /**
     * The start method creates the FXML stage and loads the initial scene.
     *
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/log-in_form.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Log-in Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application. It launches the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
