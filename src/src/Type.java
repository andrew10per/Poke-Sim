package src;
/*
defines Types and a method for the effect that each one has. one method is the only one needed to determine what
 */


public class Type {

    private String typename;
    private int typenum;
    private int typenum2;

    public Type(){
        this.typename = "Normal";
        this.typenum = 0;
    }



    /*
    This defines the main part of this class. For all 18 types, each will have a method, and a corresponding src.Type number.
    This will call a method for each type to return what it does on the other type.
     */
    static public int effect(Type defT, Type moveT){

        if (moveT.typenum == 0){
            return Normal(defT);
        }
        return 1;

    }
    //Normal method. Currently the only type in the entire game.
    static private int Normal(Type defT){
        return 1;
    }

}
