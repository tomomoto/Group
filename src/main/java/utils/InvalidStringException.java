package utils;

/**
 * Created by Tom on 26.03.2017.
 */
public class InvalidStringException extends Exception {
    private String InvalidString;

    public InvalidStringException(String invalidString) {
        super("Invalid string exception. {"+invalidString+"}");
        InvalidString = invalidString;
    }
}
