package src;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main2 {

    public static void main(String[] args) throws IOException, ParseException {
        String[] Moves = GetPokeInfo.findMovesViaID(0);

        for(String i : Moves){
            System.out.println(i);
        }

    }

}
