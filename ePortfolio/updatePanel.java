package helmi_a3.ePortfolio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * updatePanel is a JPanel that allows users to update details
 * of their existing investments in their portfolio.
 */
public class updatePanel extends JPanel {
    /**
     * Private field representing the portfolio being updated.
     */
    private Portfolio portfolio;

    /**
     * Text field for entering the updated investment name.
     */
    private JTextField nameInvestment;

    /**
     * Text field for entering the updated investment symbol.
     */
    private JTextField symbolInvestment;

    /**
     * Text field for entering the updated investment price.
     */
    private JTextField priceInvestment;

    /**
     * Text area for displaying messages during the update process.
     */
    private JTextArea messageArea;

    /**
     * Current index of the investment being updated.
     */
    private int currentIndex;

    /**
     * List of investments in the portfolio.
     */
    private ArrayList<Investment> investments;

    /**
     * Constructs an updatePanel object and initializes its components.
     *
     * @param portfolio The portfolio containing investments to be updated
     */
    public updatePanel(Portfolio portfolio) {
        this.portfolio = portfolio;
        this.investments = portfolio.getInvestments();
        this.currentIndex = 0;
        creatingComponents();
        updateFields();
    }

    /**
     * Initializes the components of the updatePanel.
     * Sets up the layout, labels, buttons, and adds them to the panel.
     */
    private void creatingComponents() {
        setLayout(new BorderLayout());

        // Create and add the label at the top
        JLabel updateLabel = new JLabel("Updating Investments");
        updateLabel.setHorizontalAlignment(JLabel.CENTER);
        updateLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        add(updateLabel, BorderLayout.NORTH);

        // Create the form panel
        JPanel updateFormPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        updateFormPanel.add(new JLabel("Symbol"));
        symbolInvestment = new JTextField();
        symbolInvestment.setEditable(false);
        updateFormPanel.add(symbolInvestment);

        updateFormPanel.add(new JLabel("Name"));
        nameInvestment = new JTextField();
        nameInvestment.setEditable(false);
        updateFormPanel.add(nameInvestment);

        updateFormPanel.add(new JLabel("Price"));
        priceInvestment = new JTextField();
        updateFormPanel.add(priceInvestment);

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");
        JButton saveButton = new JButton("Save");
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(saveButton);

        // Add the button panel below the form panel
        updateFormPanel.add(new JLabel()); // Dummy label to align the buttons
        updateFormPanel.add(buttonPanel);

        add(updateFormPanel, BorderLayout.CENTER);

        // Create and add the message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // Add scrolling
        messagePanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        messagePanel.add(scrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons
        prevButton.addActionListener(e -> showPreviousInvestment(prevButton, nextButton));
        nextButton.addActionListener(e -> showNextInvestment(prevButton, nextButton));
        saveButton.addActionListener(e -> saveInvestment());

        // Enable/disable buttons based on the current index
        prevButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < investments.size() - 1);
    }

    /**
     * Updates the fields with the details of the current investment.
     */
    private void updateFields() {
        if (!investments.isEmpty()) {
            Investment currentInvestment = investments.get(currentIndex);
            symbolInvestment.setText(currentInvestment.getSymbol());
            nameInvestment.setText(currentInvestment.getName());
            priceInvestment.setText(Double.toString(currentInvestment.getPrice()));
        }
    }

    /**
     * Shows the previous investment in the list.
     *
     * @param prevButton The previous button
     * @param nextButton The next button
     */
    private void showPreviousInvestment(JButton prevButton, JButton nextButton) {
        if (currentIndex > 0) {
            currentIndex--;
            updateFields();
            prevButton.setEnabled(currentIndex > 0);
            nextButton.setEnabled(currentIndex < investments.size() - 1);
        }
    }

    /**
     * Shows the next investment in the list.
     *
     * @param prevButton The previous button
     * @param nextButton The next button
     */
    private void showNextInvestment(JButton prevButton, JButton nextButton) {
        if (currentIndex < investments.size() - 1) {
            currentIndex++;
            updateFields();
            prevButton.setEnabled(currentIndex > 0);
            nextButton.setEnabled(currentIndex < investments.size() - 1);
        }
    }

    /**
     * Saves the updated price of the current investment.
     * Displays a message in case of invalid input.
     */
    private void saveInvestment() {
        if (!investments.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(priceInvestment.getText());
                portfolio.updateInvestmentPrice(currentIndex, newPrice);
                addMessage("Updated investment: " + investments.get(currentIndex));
            } catch (NumberFormatException e) {
                addMessage("Error: Invalid price entered.");
            }
        }
    }

    /**
     * Adds a message to the message area.
     *
     * @param message The message to add
     */
    private void addMessage(String message) {
        messageArea.append(message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}
