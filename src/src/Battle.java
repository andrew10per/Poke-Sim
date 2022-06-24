/*
This class defines a battle between 2 monsters. This is what will keep them together.

The Battle Class also runs the calculations and sets each monsters hp, stat changes, and so on.

 */
package src;


import org.json.simple.parser.ParseException;
import sample.BatController;

import java.io.IOException;

public class Battle {
    public boolean battleFin;
    private Monster p1;
    private Monster p2;
    
    public Battle(int num1, int num2) throws IOException, ParseException {
        
        battleFin = false;
        p1 = new Monster(num1,50);
        p2 = new Monster(num2,50);
        

    }


    //Getters for both p1 and p2
    public Monster getP1() {
        return p1;
    }
    public Monster getP2(){
        return p2;
    }

    /*
    For when the player chooses to attack. Calculates damage done, then it proceeds
    to call the AI for a move.
     */
    //currently need to implement speed mechanic
    public void p1Atk(Move moveSelected){
        double dmg1 = ((((((double)p1.lvl * 2.0) /5.0) + 2.0) * ((double) moveSelected.pwr * ((double) p1.atk / (double) p2.def))) / 50.0) + 2.0;//DAMAGE CALCULATION BEFORE STAB AND TYPE BOOSTS
        int dmg = (int) dmg1;
        p2.hp = p2.hp - dmg;
        checkBattle();
        p2Atk();
        checkBattle();
    }

    private void p2Atk() {
        double dmg1 = ((((((double)p2.lvl * 2.0) /5.0) + 2.0) * ((double) p2.moves[0].pwr * ((double) p2.atk / (double) p1.def))) / 50.0) + 2.0;
        int dmg = (int) dmg1;
        p1.hp = p1.hp - dmg;
    }

    private void checkBattle() {
        if (p2.hp <= 0 || p1.hp <= 0){
            battleFin = true;

        }
    }


}
