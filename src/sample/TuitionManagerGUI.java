package sample;
/**
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TuitionManagerGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Loads GUI in from the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // Set window title, window size, make it nonresizable, and finally show the window
        primaryStage.setTitle("Program 3 - Tuition Manager");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
