package helmi_a3.ePortfolio;

import javax.swing.*; // this includes JFrame, JPanel, JButton, JTextArea

import java.awt.*;

/**
 * * WelcomePanel is a JPanel that displays a welcome message and instructions *
 * for using the ePortfolio application.
 */
public class WelcomePanel extends JPanel {

    // constructor for the welcome panel window
    /**
     * Constructs a WelcomePanel object and initializes its components.
     */
    public WelcomePanel() {
        // calling the components function
        initializeComponents();
    }

    /**
     * Initializes the components of the WelcomePanel.
     * Sets up the layout, labels, and adds them to the panel.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());
        // creating the welcome panel label
        JLabel label = new JLabel("Welcome to ePortfolio!");
        label.setHorizontalAlignment(JLabel.CENTER); // center align the label
        label.setFont(new Font("Serif", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Adding an area for text welcoming the user to the program with similar font
        // size
        JLabel instructions = new JLabel("<html>Choose a command from the “Commands” menu to buy or sell<br>"
                + "an investment, update prices for all investments, get gain for the<br>"
                + "portfolio, search for relevant investments, or quit the program</html>");
        instructions.setHorizontalAlignment(JLabel.CENTER); // Center align the label
        instructions.setFont(new Font("Serif", Font.PLAIN, 20)); // Set font size and style
        add(instructions, BorderLayout.CENTER);
    }
}
