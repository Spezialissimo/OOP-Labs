package it.unibo.mvc;

import java.util.List;

/**
 * Simple controller responsible of I/O access.
 */
public interface Controller {
    /**
     * Sets the next string that has to be printed.
     * @param newString is the next string.
     */
    void setNextString(String newString);
    /**
     * @return next string that has to be printed.
     */
    String getNextString();
    /**
     * @return a list containg all the string that has been printed.
     */
    List<String> getHistory();
    /**
     * Prints the current string.
     * @throws IllegalArgumentException
     */
    void printCurrentString();
}
