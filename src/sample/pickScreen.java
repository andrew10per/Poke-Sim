package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class pickScreen {


    public static int n1;
    public static int n2;

    @FXML
    private Button battleBut;

    @FXML
    private TextField enemyInpBox;

    @FXML
    private TextField userInpBox;



    @FXML
    void goToBattle(ActionEvent event) throws IOException {
        n1 = Integer.parseInt(userInpBox.getText());
        n2 = Integer.parseInt(enemyInpBox.getText());

        Stage stageButtonisOn = (Stage) battleBut.getScene().getWindow(); //getter to get the current stage(window) that the button is on
        Parent root = FXMLLoader.load(getClass().getResource("batscreen.fxml")); //creates a parent called "root" which can be used to open a new scene file.
        stageButtonisOn.setScene(new Scene(root, 600, 400)); //sets the scene to a new scene, of the same size


    }

}

