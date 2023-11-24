package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI extends BaseGUI {
    /**
     * It creates a GUI as specified in the README with
     * a button that saves the inserted text in the file
     * using the controller {@code .writeOnFile}.
     * @param controller
     */
    public SimpleGUI(final Controller controller) {
        final JFrame frame = getFrame();
        final JPanel panel = new JPanel();
        final JTextArea tArea = new JTextArea();
        final JButton save = new JButton("Save");
        panel.setLayout(new BorderLayout());
        panel.add(tArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.writeOnFile(tArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(panel, "Error in getting text from the textArea" + e); // NOPMD
                }
            }
        });
    }
    /**
     * main.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI(new Controller()).display();
    }
}
