package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    
    private static final int RANGE_START = 1000;
    private static final int RANGE_END = 2000;
    private static final int ELEMS_ADD_BENCH = 100_000;
    private static final int ELEMS_GET_MIDDLE = 1000;
    
    private UseListsAndMaps() {
    }

    private static long runBenchAddHead(final List<Integer> list) {
        long time = System.nanoTime();
        for (int i = 1; i <= ELEMS_ADD_BENCH; i++) {
            list.add(0, i);
        }
        time = System.nanoTime() - time;
        return TimeUnit.NANOSECONDS.toMillis(time);
    }

    private static long runBenchGetMiddle(final List<Integer> list) {
        long time = System.nanoTime();
        for (int i = 1; i <= ELEMS_GET_MIDDLE; i++) {
            list.get(list.size() / 2);
        }
        time = System.nanoTime() - time;
        return TimeUnit.NANOSECONDS.toMillis(time);
    }


    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> population1 = new ArrayList<>();
        for (int i = RANGE_START; i < RANGE_END; i++) {
            population1.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> population2 = new LinkedList<>(population1);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int tmp = population1.get(0);
        population1.set(0, population1.get(population1.size() - 1));
        population1.set(population1.size() - 1, tmp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int integer : population2) {
            System.out.println(integer);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        System.out.println("Primo bench ArrayList ci ha messo " + runBenchAddHead(population1) + " ms");
        System.out.println("Primo bench LinkedList ci ha messo " + runBenchAddHead(population2) + " ms");
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        System.out.println("Secondo bench ArrayList ci ha messo " + runBenchGetMiddle(population1) + " ms");
        System.out.println("Secondo bench LinkedList ci ha messo " + runBenchGetMiddle(population2) + " ms");
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> world = new HashMap<>();
        world.put("Africa", 1_110_635_000l);
        world.put("Americas", 972_005_000l);
        world.put("Antarctica", 0l);
        world.put("Asia", 4_298_723_000l);
        world.put("Europe", 742_452_000l);
        world.put("Oceania", 38_304_000l);
        /*
         * 8) Compute the population of the world
         */
        long total_population = 0;
        for (final long elem : world.values()) {
            total_population += elem;
        }
        System.out.println("La popolazione totale è " + total_population);
    }
}
