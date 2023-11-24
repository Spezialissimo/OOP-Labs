package it.unibo.mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;

    private final List<DrawNumberView> views;
    private DrawNumber model;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final String configFile,final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        
        getConfig(configFile);
    }

    private void getConfig(final String configFile) {
        final String configPathString = ClassLoader.getSystemResource(configFile).getPath();
        final Path configPath = Paths.get(configPathString);
        Optional<Integer> min = Optional.empty();
        Optional<Integer> max = Optional.empty();
        Optional<Integer> attempts = Optional.empty();
        if (Files.exists(configPath)) {            
            try{
                for (String line : Files.readAllLines(configPath, StandardCharsets.UTF_8)) {
                    String name = line.split(": ")[0];
                    int value = Integer.parseInt(line.split(": ")[1]);
                    switch (name) 
                    {
                        case "minimum":
                            min = Optional.of(value);
                            break;
                        case "maximum":
                            max = Optional.of(value);
                            break;
                        case "attempts":
                            attempts = Optional.of(value);
                            break;
                        default: 
                            throw new IllegalArgumentException("Config file not valid");
                    }
                }
            } catch (Exception e)
            {        
                for (DrawNumberView view : views) {
                    view.displayError(e.toString());
                }
            }
        }
        
        if(min.isEmpty() || max.isEmpty() || attempts.isEmpty()) {
            this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        }
        else {
            this.model = new DrawNumberImpl(min.get().intValue(), max.get().intValue(), attempts.get().intValue());
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {            
        new DrawNumberApp("config.yml", // res is part of the classpath!
                new DrawNumberViewImpl(),
                new DrawNumberViewImpl(),
                new PrintStreamView(System.out),
                new PrintStreamView("output.log"));
    }

}
