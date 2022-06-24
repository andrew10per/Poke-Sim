package src;
/*
src.Monster class defines a "src.Monster". Currently there is no special attack or special defense. Name, level, speed, hp, atk and defense are the stats.
src.Type, and src.Move are two other defined classes. src.Type will be used to calculate the actual type of the pokemon whereas the rest will be used to
 */

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Monster{

    //Instance Variables
    public String name; //Name of Mon
    public int number; //dex number
    public int maxHP; //max hp
    public int hp; //current hit points
    public int lvl; //level
    public int atk; //attack
    public int def; //Defense
    public int speed; //speed stat
    public Type type1; //Typing
    public Type type2; //Secondary typing
    public Move[] moves; //Moves


    //Default constructor
    public Monster(){

        this.name = "Test";
        this.number = 1;
        this.hp = 20;
        this.maxHP = this.hp;
        this.atk = 1;
        this.lvl = 1;
        this.def = 1;
        this.speed = 1;
        this.moves = new Move[4];
        this.moves[0] = new Move("Scratch",40,100,0, "Normal");
        this.moves[1] = new Move();
        this.moves[2] = new Move();
        this.moves[3] = new Move();
    }

    //This is the most important constructor. Gives you the Pokemon from the JSON database.
    public Monster(int num, int lvl) throws IOException, ParseException {

        this.name = GetPokeInfo.findNameViaID(num - 1);
        this.number = num;
        this.lvl = lvl;
        int[] stats = GetPokeInfo.findStatsViaID(num -1);
        this.hp = getCurStat(stats[0],lvl) + lvl + 5; //HP has a different function for its stat lol
        this.atk = getCurStat(stats[1],lvl);
        this.def = getCurStat(stats[2],lvl);
        this.speed = getCurStat(stats[3],lvl);
        this.maxHP = this.hp;
        this.moves = new Move[4];
        this.moves[0] = GetPokeInfo.getRandomMove();
        this.moves[1] = GetPokeInfo.getRandomMove();
        this.moves[2] = GetPokeInfo.getRandomMove();
        this.moves[3] = GetPokeInfo.getRandomMove();
    }



    //Constructor for a custom...EVERYTHING.
    public Monster(String name, int number, int maxHP, int atk, int lvl, int def, int speed, Move[] moves){
        this.name = name;
        this.number = number;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.atk = atk;
        this.lvl = lvl;
        this.def = def;
        this.speed = speed;
        this.moves = moves;
    }

    public static int getCurStat(int base, int lvl){
        double stat = (((2.0 * (double)base) * (double)lvl) / 100.0) + 5.0;
        return (int) stat;
    }

}
