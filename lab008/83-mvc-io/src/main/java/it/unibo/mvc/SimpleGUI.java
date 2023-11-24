package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI extends BaseGUI {
    /**
     * It creates a simple GUI as specified in the README.
     * @param controller
     */
    public SimpleGUI(final Controller controller) {
        final JFrame frame = getFrame();
        final JPanel panel = new JPanel();
        final JPanel buttonPanel = new JPanel();
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");

        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(print, BorderLayout.EAST);
        buttonPanel.add(history, BorderLayout.WEST);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                controller.setNextString(textField.getText());
                controller.printCurrentString();
            }
        });

        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                controller.getHistory().forEach(x -> textArea.append(x + "\n"));
            }
        });
    }

    /**
     * Main.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }
}
