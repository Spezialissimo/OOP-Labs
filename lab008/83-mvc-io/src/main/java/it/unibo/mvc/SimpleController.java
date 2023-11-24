package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private static final String EMPTY_STRING = "";
    private final List<String> history = new ArrayList<>();
    private String nextString = EMPTY_STRING;

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public void printCurrentString() {
        System.out.println(nextString); // NOPMD: We are purposely trying to print on stout.
    }

    @Override
    public void setNextString(final String newString) {
        Objects.requireNonNull(newString);
        this.nextString = newString;
        this.history.add(newString);
    }

}
