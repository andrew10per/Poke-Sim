package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class Controller {
    //corresponds to button ID.
    public Button startButton;


    /*
    Function to swap the scene, based on the button that is pressed.
    Responds to the single player button. Used "fx:id =" in the XML file to set an ID for the button.
     */
    @FXML
    void startbutton(ActionEvent event) throws IOException {
        Stage stageButtonisOn = (Stage) startButton.getScene().getWindow(); //getter to get the current stage(window) that the button is on
        Parent root = FXMLLoader.load(getClass().getResource("pickScreen.fxml")); //creates a parent called "root" which can be used to open a new scene file.
        stageButtonisOn.setScene(new Scene(root, 600, 400)); //sets the scene to a new scene, of the same size

    }

}
