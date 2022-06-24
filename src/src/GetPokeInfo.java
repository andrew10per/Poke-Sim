package src;
import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GetPokeInfo {

    public static String findNameViaID(int id) throws IOException, org.json.simple.parser.ParseException {

        Object obj = new JSONParser().parse(new FileReader("src/resources/JSON/pokes.json"));
        //JO is the initial entire array.
        //jo2 is the JSON holding the pokemon of dexNumber -1.
        JSONArray jo = (JSONArray) obj;
        JSONObject jo2 = (JSONObject) jo.get(id);

        //names gathers all names in each language
        JSONObject names = (JSONObject) jo2.get("name");
        String name = (String) names.get("english"); //pulls the english name into a variable called name

        return name;
    }

    public static int[] findStatsViaID(int id) throws IOException, org.json.simple.parser.ParseException {

        Object obj = new JSONParser().parse(new FileReader("src/resources/JSON/pokes.json"));
        //JO is the initial entire array.
        //jo2 is the JSON holding the pokemon of dexNumber -1.
        JSONArray jo = (JSONArray) obj;
        JSONObject jo2 = (JSONObject) jo.get(id);

        //jostats holds the json that has all base stat numbers

        JSONObject jostats = (JSONObject) jo2.get("base");
        int[] stats = new int[4];

        //FOR WHATEVER UGLY REASON
        //The JSON parser pulls ints out as Long values. only way to cast to int is to do the following..ugly I know.
        //Takes 2 commands which is stupid
        Long myLong = (Long) jostats.get("HP");stats[0] = myLong.intValue();

        myLong= (Long) jostats.get("Attack");stats[1] = myLong.intValue();

        myLong= (Long) jostats.get("Defense");stats[2] = myLong.intValue();

        myLong= (Long) jostats.get("Speed");stats[3] = myLong.intValue();

        return stats;
    }

    //Finds the pokemon's moves that are associated with it in the given database.
    public static String[] findMovesViaID(int id) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/resources/JSON/pokes.json"));
        //JO is the initial entire array.
        //jo2 is the JSON holding the pokemon of dexNumber -1.
        JSONArray jo = (JSONArray) obj;
        JSONObject jo2 = (JSONObject) jo.get(id);

        //jostats holds the json that has all base stat numbers

        JSONArray jomoves = (JSONArray) jo2.get("Moves");
        String[] moves = new String[4];

        for (int i = 0; i < 4; i++){
            moves[i] = (String) jomoves.get(i);
        }

        return moves;
    }

    //Returns a random move for the pokemon to use. Gen 7 moves are used since that was the best DB I could find in json format.
    public static Move getRandomMove() throws IOException, ParseException {
        Random rand = new Random();
        int id = rand.nextInt(500);


        Object obj = new JSONParser().parse(new FileReader("src/resources/JSON/moves.json"));

        //need to get a list of keys then use the random number to get the array key.
        JSONObject jo = (JSONObject) obj;
        Set keys = jo.keySet(); //Makes all the keys a set.

        //Puts all the keys in a random order into an Iterator, we pull the first one.
        Iterator it = keys.iterator();

        String idn = "";
        for (int i = 0; i < id; i++) {
            idn = (String) it.next();
        }

        JSONObject jo2 = (JSONObject) jo.get(idn);
        System.out.println(idn);
        String n = (String) jo2.get("name");
        String t = (String) jo2.get("type");

        Long pwr = (Long) jo2.get("basePower");
        Long acc = (Long) jo2.get("accuracy");

        if (pwr == null){
            pwr = new Long(0);
        }
        if (acc == null){
            acc = new Long(0);
        }

        return new Move(n,pwr.intValue(), acc.intValue(), 0, t);

    }
}