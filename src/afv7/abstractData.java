package afv7;

import java.awt.*;
import java.util.Set;

/**
 * Custom exception, doesn't get used
 */
class UnknownSymbolException extends Exception {

    public UnknownSymbolException(String error){
        super(error);
    }
// Custom except die steeds op de verkeerde plaats geactiveerd werd :c
}

/**
 * Broken function that doesn't get called
 */
public abstract class abstractData extends nucleotideVisualisator {
    // 1 = rood
    // 2 = blauw
    // 3 = grijs
    // 4 = geel

    /**
     * The idea at the time was...
     * Doesn't get used
     * @param number
     * @return colour in string format
     */
    public String whichColour(int number){
        String colour = "";

        if (number == 1){colour = "RED";}
        if (number == 2){colour = "BLUE";}
        if (number == 3){colour = "GRAY";}
        if (number == 4){colour = "YELLOW";}
        return (colour);

    }
}
