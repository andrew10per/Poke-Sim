package src;
/*
Defines moves of a pokemon. Moves have an attack power, as well as a typing, accuracy, and a heals stat for if the user gains HP back.
Moves have names also.

Going to need to develop a .JSON Database of sorts to hold moves once we get further along in the game.
 */


public class Move {
    public String name; //the name of attack
    public int pwr; //attack power
    public int acc; //accuracy
    public int heals; //if the attack heals
    public String type;


    //Default constructor
    public Move(){
        this.name = "";
        this.pwr = 0;
        this.acc = 0;
        this.heals = 0;

    }

    //provides a constructor for actually inputting a move.
    public Move(String name, int pwr, int acc, int heals, String type){
        this.name = name;
        this.pwr = pwr;
        this.acc = acc;
        this.heals = heals;
        this.type = type;

    }




}
