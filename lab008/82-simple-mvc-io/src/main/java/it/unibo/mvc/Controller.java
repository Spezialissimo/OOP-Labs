package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    static final String HOME_DIR = System.getProperty("user.home");
    static final String FILE_SEP = System.getProperty("file.separator");
    static final Path CURRENT_PATH = Paths.get(HOME_DIR + FILE_SEP + "output.txt");
    private File currentFile = new File(CURRENT_PATH.toString());
    /**
     * Sets the current file using the one given as argument.
     * @param newFile
     */
    public final void setCurrentFile(final File newFile) {
        this.currentFile = newFile;
    }
    /**
     * Gets the current file.
     * @return the current file.
     */
    public final File getCurrentFile() {
        return this.currentFile;
    }
    /**
     * gets the path of the current file as a string.
     * @return the path of the current file as a string.
     */
    public final String getPathAsString() {
        return this.currentFile.getPath();
    }
    /**
     * Writes the {@code text} provided as argument on 
     * the current file.
     * @param text
     * @throws IOException
     */
    public final void writeOnFile(final String text) throws IOException {
        final Path myPath = Paths.get(this.getPathAsString());
        Files.writeString(myPath, text, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }
}
