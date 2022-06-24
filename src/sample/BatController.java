package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.simple.parser.ParseException;
import src.Battle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BatController implements Initializable {

    @FXML
    private ProgressBar playerLifeBar;

    @FXML
    private ProgressBar enemyLifeBar;

    @FXML
    private Button move0; //first move

    @FXML
    private Button move1; //second move

    @FXML
    private Button move2; //third move

    @FXML
    private Button move3; //4th move

    @FXML
    private Button attack; //attack button

    @FXML
    private Button bag; //go to bag button

    @FXML
    private Label hp1; //hp label for pokemon1

    @FXML
    private Label hp2; //hp label for pokemon2

    @FXML
    private Button monsters; //button to go to other available party members

    @FXML
    private Label nameM1; //name label for poke1

    @FXML
    private Label nameM2; //name label for poke2

    @FXML
    private ImageView p1img; //image for poke1

    @FXML
    private ImageView p2img; //image for poke2

    @FXML
    private Button run; //run button

    @FXML
    private TilePane attackPanel; //panel that holds the attacks 1-4

    public Battle battle; //our battle that is instantiated upon pressing of the single playher button


    //Getter for the battle that is created.
    public Battle getBattle() {
        return battle;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Try catch blocks for ioexceptions.
        try {
            battle = new Battle(pickScreen.n1, pickScreen.n2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hp1.setText(String.valueOf(battle.getP1().hp) + " / " + String.valueOf(battle.getP1().maxHP)); //sets the hp of monster 1
        hp2.setText(String.valueOf(battle.getP2().hp) + " / " + String.valueOf(battle.getP2().maxHP)); //sets the hp label of monster 2

        //This Block sets the names of 1 and 2.
        nameM1.setText(battle.getP1().name);
        nameM2.setText(battle.getP2().name);

        /*
        This sets the image based on pokedex number.
        Oddly enough, there is no way to just take an ImageView object and change the URL. We need to grab an Image object and essentially
        Wrap the image object in the URL
        */
        Image image = new Image(getClass().getResourceAsStream("../resources/back/" + String.valueOf(battle.getP1().number) + ".png"));
        Image image2 = new Image(getClass().getResourceAsStream("../resources/" + String.valueOf(battle.getP2().number) + ".png"));
        p1img.setImage(image); // setting the image based on pokedex num (which corresponds to the sprite's image number)
        p2img.setImage(image2); //ditto to above, but for the second pokemon's sprite

        //Here we set the text of the buttons that are in the attack spots.
        move0.setText(battle.getP1().moves[0].name);
        move1.setText(battle.getP1().moves[1].name);
        move2.setText(battle.getP1().moves[2].name);
        move3.setText(battle.getP1().moves[3].name);

        //Setting the attacks that should be invisible to be invisible.
        if (move0.getText() == ""){
            move0.setVisible(false);
        }
        if (move1.getText() == ""){
            move1.setVisible(false);
        }
        if (move2.getText() == ""){
            move2.setVisible(false);
        }
        if (move3.getText() == ""){
            move3.setVisible(false);
        }

        //Life Bar percentages are calculated here
        enemyLifeBar.setProgress((double) battle.getP2().hp / (double) battle.getP2().maxHP);
        playerLifeBar.setProgress((double) battle.getP1().hp / (double)battle.getP1().maxHP);
    }

    /*
    Sends GUI into state where user is now picking a move from the moves that it has available.
    Once a move is then selected, the battle round is executed. If a move is not selected, it waits.
     */
    @FXML
    void ChooseAtk(ActionEvent event){
        if (attackPanel.isVisible()){
            attackPanel.setVisible(false);
        }else {
            attackPanel.setVisible(true);
        }


    }

    /*
    Currently, this chooses a move when a button is selected. It will currently choose move 0, which is Scratch, no matter what.
    all the atk function does is deal damage to the opponent.
     */
    @FXML
    void attackChosen(ActionEvent event){

        if (event.getSource() == move0){
            battle.p1Atk(battle.getP1().moves[0]);
        }
        else if (event.getSource() == move1){
            battle.p1Atk(battle.getP1().moves[1]);
        }
        else if (event.getSource() == move2){
            battle.p1Atk(battle.getP1().moves[2]);
        }
        else if (event.getSource() == move3){
            battle.p1Atk(battle.getP1().moves[3]);
        }
        updateGUI();

    }


    /*
    Update's GUI with things like hp, and moves no longer visible.
     */
    private void updateGUI() {
        hp1.setText(String.valueOf(battle.getP1().hp) + " / " + String.valueOf(battle.getP1().maxHP)); //sets the hp of monster 1
        hp2.setText(String.valueOf(battle.getP2().hp) + " / " + String.valueOf(battle.getP2().maxHP)); //sets the hp label of monster 2

        attackPanel.setVisible(false);

        //Life Bar percentages are calculated here
        enemyLifeBar.setProgress((double) battle.getP2().hp / battle.getP2().maxHP);
        playerLifeBar.setProgress((double) battle.getP1().hp / battle.getP1().maxHP);

        //sets the color of the life bar.
        if (enemyLifeBar.getProgress() < 0.2){enemyLifeBar.setStyle("-fx-accent: red;");}else if (enemyLifeBar.getProgress() < 0.5){enemyLifeBar.setStyle("-fx-accent: yellow;");}
        if (playerLifeBar.getProgress() < 0.2){playerLifeBar.setStyle("-fx-accent: red;");}else if (playerLifeBar.getProgress() < 0.5){playerLifeBar.setStyle("-fx-accent: yellow;");}

        if (battle.battleFin){
            Stage stage = (Stage) hp1.getScene().getWindow();
            stage.close();

        }
    }

}
