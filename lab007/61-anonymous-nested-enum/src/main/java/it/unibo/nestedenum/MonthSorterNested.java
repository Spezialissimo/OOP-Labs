package it.unibo.nestedenum;

import java.time.Month;
import java.util.Comparator;

import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DAYS = new SortByDays();
    private static final Comparator<String> BY_ORDER = new SortByOrder();

    private static class SortByDays implements Comparator<String> {    
        @Override
        public int compare(String arg0, String arg1) {
            return Integer.compare(Month.fromString(arg0).numOfDays, Month.fromString(arg1).numOfDays);
        }        
    }

    private static class SortByOrder implements Comparator<String> {    
        @Override
        public int compare(String arg0, String arg1) {
            return Month.fromString(arg0).compareTo(Month.fromString(arg1));
        }        
    }

    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private int numOfDays;

        private Month(final int numOfDays) {
            this.numOfDays = numOfDays;
        }
        
        public static Month fromString(final String monthName) {
            Month result = null;            
            for (Month elem : Month.values()) {
                if (elem.corresponds(monthName)) {
                    if (result != null) {
                        throw new IllegalArgumentException("Errore");
                    }
                    result = elem;
                }
            }
            if (result == null) {
                throw new IllegalArgumentException("Errore");
            }
            return result;
        }

        private boolean corresponds(String monthName) {
            return this.toString().toLowerCase().startsWith(monthName.toLowerCase());
        }

        public int getNumOfDays() {
            return numOfDays;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }
}    

