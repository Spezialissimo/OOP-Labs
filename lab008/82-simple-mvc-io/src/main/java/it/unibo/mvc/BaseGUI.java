package it.unibo.mvc;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * In the real world we woundn't need to have 2 slightly different classes like
 * we have with SimpleGUI and SimpleGUIWithFileChooser, but for the sake of
 * exercise we'll still have them in the  same project. This class has the
 * standard implementation for a GUI based on SimpleGUI hust to avoid copy and
 * pasted code.
 */
public class BaseGUI {
    private static final int PROPORTION = 2;
    private final JFrame frame = new JFrame();
    /**
     * Getter of the {@code JFrame} of the GUI.
     * @return {@code JFrame} of the GUI
     */
    protected JFrame getFrame() {
        return this.frame;
    }
    /** 
     * Makes the GUI visibile and sets the size of the 
     * window five times smaller as the screen.
     */
    protected void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
