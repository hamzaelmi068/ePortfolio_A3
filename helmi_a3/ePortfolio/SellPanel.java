package helmi_a3.ePortfolio;

import javax.swing.*;
import java.awt.*;

/**
 * Sell Panel is a JPanel that allows users to input details
 * to sell a new investment for their portfolio.
 */
public class SellPanel extends JPanel {
    /**
     * Private field representing the portfolio from which investments are sold.
     */
    private Portfolio portfolio;

    /**
     * Text field for entering the investment symbol to sell.
     */
    private JTextField symbolField;

    /**
     * Text field for entering the quantity of investment to sell.
     */
    private JTextField quantityField;

    /**
     * Text field for entering the selling price.
     */
    private JTextField priceField;

    /**
     * Text area for displaying messages related to the sale.
     */
    private JTextArea messageArea;

    // constructor
    /**
     * Constructs a Sell panel object and initializes its components.
     *
     * @param portfolio The portfolio to add new investments to
     */
    public SellPanel(Portfolio portfolio) {
        this.portfolio = portfolio;
        creatingComponents();
    }

    /**
     * creates the components of the Sell Panel.
     * Sets up the layout, labels, buttons, and adds them to the panel.
     */
    private void creatingComponents() {
        setLayout(new BorderLayout());

        // create and adding the sell label at the top
        JLabel sellLabel = new JLabel("Selling an Investment");
        sellLabel.setHorizontalAlignment(JLabel.CENTER);
        sellLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(sellLabel, BorderLayout.NORTH);

        // creating the form panel
        JPanel sellFormPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        // adding the components to the form panel
        sellFormPanel.add(new JLabel("Symbol"));
        symbolField = new JTextField();
        sellFormPanel.add(symbolField);

        sellFormPanel.add(new JLabel("Quantity"));
        quantityField = new JTextField();
        sellFormPanel.add(quantityField);

        sellFormPanel.add(new JLabel("Price"));
        priceField = new JTextField();
        sellFormPanel.add(priceField);

        // creating the button panel for the reset button and sell button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton sellInvestButton = new JButton("Sell");
        JButton resetInvestmentButton = new JButton("Reset");
        buttonPanel.add(sellInvestButton);
        buttonPanel.add(resetInvestmentButton);

        // adding the button panel below the price label in the form panel
        sellFormPanel.add(new JLabel()); // Empty label for alignment
        sellFormPanel.add(buttonPanel);

        // adding the form panel to the main panel
        add(sellFormPanel, BorderLayout.CENTER);

        // creating the message panel below
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // making sure it's read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // adding scrolling
        messagePanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        messagePanel.add(scrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons
        sellInvestButton.addActionListener(e -> {
            try {
                String symbol = symbolField.getText().toUpperCase().trim();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                // validity checks
                if (symbol.isEmpty())
                    throw new IllegalArgumentException("Symbol cant be empty");

                portfolio.sellInvestment(symbol, quantity, price);
                addMessage("Attempted to sell " + quantity + " units of " + symbol + " at $" + price);
            } catch (NumberFormatException ex) {
                addMessage(
                        "Invalid input for quantity or price, or symbol. Please enter numeric values & a suitable Symbol.");
            } catch (IllegalArgumentException ex) {
                addMessage("Error: " + ex.getMessage());
            }
        });

        resetInvestmentButton.addActionListener(e -> resetForm());
    }

    /**
     * Resets all input fields to their default empty states for sell panel
     */
    private void resetForm() {
        symbolField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    /**
     * Adds a message to the message area for sell panel
     *
     * @param message The message to add
     */
    private void addMessage(String message) {
        messageArea.append(message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}
