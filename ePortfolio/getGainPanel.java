package helmi_a3.ePortfolio;

import javax.swing.*;
import java.awt.*;

/**
 * getGainPanel is a JPanel that allows users to get the gain of investments
 * of their existing investments in their portfolio.
 */
public class getGainPanel extends JPanel {
    /**
     * Private field representing the portfolio being analyzed for gains.
     */
    private Portfolio portfolio;

    /**
     * Text field for displaying the total gain of the portfolio.
     */
    private JTextField totalGain;

    /**
     * Text area for displaying individual investment gains.
     */
    private JTextArea individualGainsMessageArea;

    /**
     * Constructs a getGainPanel object and initializes its components.
     *
     * @param portfolio The portfolio from which gains will be calculated
     */
    public getGainPanel(Portfolio portfolio) {
        this.portfolio = portfolio;
        creatingComponents();
        displayGains();
    }

    /**
     * Initializes the components of the getGainPanel.
     * Sets up the layout, labels, text areas, and adds them to the panel.
     */
    public void creatingComponents() {
        setLayout(new BorderLayout());

        // Create and add the label at the top
        JLabel getGainLabel = new JLabel("Getting Total Gain");
        getGainLabel.setHorizontalAlignment(JLabel.CENTER);
        getGainLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        add(getGainLabel, BorderLayout.NORTH);

        // Create the panel for total gain
        JPanel getGainPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        getGainPanel.add(new JLabel("Total Gain:"));
        totalGain = new JTextField();
        totalGain.setEditable(false);
        getGainPanel.add(totalGain);
        add(getGainPanel, BorderLayout.CENTER);

        // Create the message panel for individual gains
        JPanel messagePanel = new JPanel(new BorderLayout());
        individualGainsMessageArea = new JTextArea(20, 30);
        individualGainsMessageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(individualGainsMessageArea); // Add scrolling
        messagePanel.add(new JLabel("Individual Gains"), BorderLayout.NORTH);
        messagePanel.add(scrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    /**
     * Displays the total and individual gains in the respective text areas.
     */
    public void displayGains() {
        double gain = portfolio.getTotalGain();
        totalGain.setText(String.format("%.2f", gain));
        individualGainsMessageArea.setText(portfolio.getIndividualGains());
    }
}
