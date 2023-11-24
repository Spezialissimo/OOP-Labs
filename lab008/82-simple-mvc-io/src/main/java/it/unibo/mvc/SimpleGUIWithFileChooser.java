package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser extends BaseGUI {
    /**
     * It creates a GUI as specified in the README. 
     * it has a button that saves the inserted text in the file
     * using the controller {@code .writeOnFile} and the 
     * ability to change the file browsing the file system
     * by the user.
     * @param controller
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JFrame frame = getFrame();
        final JPanel panel = new JPanel();
        final JTextArea tArea = new JTextArea();
        final JButton save = new JButton("Save");
        final JPanel browseJPanel = new JPanel();
        final JTextField browseTextField = new JTextField(controller.getPathAsString());
        final JButton browseButton = new JButton("Browse...");
        panel.setLayout(new BorderLayout());
        panel.add(tArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        panel.add(browseJPanel, BorderLayout.NORTH);
        browseTextField.setEditable(false);
        browseJPanel.setLayout(new BorderLayout());
        browseJPanel.add(browseTextField, BorderLayout.CENTER);
        browseJPanel.add(browseButton, BorderLayout.EAST);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFileChooser chooser = new JFileChooser(controller.getPathAsString());
                final int result = chooser.showDialog(browseButton, "ok");
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(chooser.getSelectedFile());
                    browseTextField.setText(controller.getPathAsString());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(panel, "Error, couldn't get file");
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.writeOnFile(tArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(panel, "Error in getting text from the textArea" + e);
                }
            }
        });
    }
    /**
     * main.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
