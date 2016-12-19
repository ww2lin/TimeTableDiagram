package Util;

/**
 * Created by AlexLin on 12/18/16.
 */
public class Util {

    public static int parseInteger(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
