package helmi_a3.ePortfolio;

import javax.swing.*;
import java.awt.*;

/**
 * BuyInvestmentPanel is a JPanel that allows users to input details
 * to buy a new investment for their portfolio.
 */
public class BuyInvestmentPanel extends JPanel {
    /**
     * Private field representing the portfolio being managed.
     */
    private Portfolio portfolio;

    /**
     * Text field for entering the name of the investment.
     */
    private JTextField nameInvestmentField;

    /**
     * Text field for entering the investment's symbol.
     */
    private JTextField symbolField;

    /**
     * Text field for entering the investment's quantity.
     */
    private JTextField quantityInvestmentTextField;

    /**
     * Text field for entering the investment's price.
     */
    private JTextField priceInvestmentTextField;

    /**
     * Text area for displaying messages related to the investment purchase.
     */
    private JTextArea messageArea;

    /**
     * Constructs a BuyInvestmentPanel object and initializes its components.
     *
     * @param portfolio The portfolio to add new investments to
     */
    public BuyInvestmentPanel(Portfolio portfolio) {
        this.portfolio = portfolio;
        initializeComponents();
    }

    /**
     * Initializes the components of the BuyInvestmentPanel.
     * Sets up the layout, labels, buttons, and adds them to the panel.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Create and add the label at the top
        JLabel buyLabel = new JLabel("Buying an Investment");
        buyLabel.setHorizontalAlignment(JLabel.CENTER);
        buyLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        add(buyLabel, BorderLayout.NORTH);

        // Create the form panel
        JPanel buyFormPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // Updated GridLayout

        // Add components to the form panel
        buyFormPanel.add(new JLabel("Type"));
        JComboBox<String> typeComboBox = new JComboBox<>(new String[] { "Stock", "MutualFund" });
        buyFormPanel.add(typeComboBox);

        buyFormPanel.add(new JLabel("Symbol"));
        symbolField = new JTextField();
        buyFormPanel.add(symbolField);

        buyFormPanel.add(new JLabel("Name"));
        nameInvestmentField = new JTextField();
        buyFormPanel.add(nameInvestmentField);

        buyFormPanel.add(new JLabel("Quantity"));
        quantityInvestmentTextField = new JTextField();
        buyFormPanel.add(quantityInvestmentTextField);

        buyFormPanel.add(new JLabel("Price"));
        priceInvestmentTextField = new JTextField();
        buyFormPanel.add(priceInvestmentTextField);

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buyInvestmentButton = new JButton("Buy");
        JButton resetInvestmentButton = new JButton("Reset");
        buttonPanel.add(buyInvestmentButton);
        buttonPanel.add(resetInvestmentButton);

        // Add the button panel below the price label in the form panel
        buyFormPanel.add(new JLabel()); // Dummy label to align the buttons
        buyFormPanel.add(buttonPanel);

        // Add action listeners for the buttons
        buyInvestmentButton.addActionListener(e -> {
            try {
                String type = typeComboBox.getSelectedItem().toString();
                String symbol = symbolField.getText().trim().toUpperCase();
                String name = nameInvestmentField.getText().trim();
                int quantity = Integer.parseInt(quantityInvestmentTextField.getText().trim());
                double price = Double.parseDouble(priceInvestmentTextField.getText().trim());

                // Validity checks
                if (symbol.isEmpty())
                    throw new IllegalArgumentException("Symbol cannot be empty.");
                if (name.isEmpty())
                    throw new IllegalArgumentException("Name cannot be empty.");
                if (quantity <= 0)
                    throw new IllegalArgumentException("Quantity must be positive.");
                if (price <= 0)
                    throw new IllegalArgumentException("Price must be positive.");

                portfolio.buyInvestment(type, symbol, name, quantity, price);
                addMessage("Bought " + type + " with symbol " + symbol + " and name " + name);
            } catch (NumberFormatException ex) {
                addMessage("Invalid input for quantity or price. Please enter numeric values.");
            } catch (IllegalArgumentException ex) {
                addMessage("Error: " + ex.getMessage());
            }
        });

        resetInvestmentButton.addActionListener(e -> resetForm());

        // Add the form panel to the main panel
        add(buyFormPanel, BorderLayout.CENTER);

        // Create and add the message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // Add scrolling
        messagePanel.add(new JLabel("Messages:"), BorderLayout.NORTH);
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.SOUTH);
    }

    /**
     * Resets all input fields to their default empty state.
     */
    private void resetForm() {
        symbolField.setText("");
        nameInvestmentField.setText("");
        quantityInvestmentTextField.setText("");
        priceInvestmentTextField.setText("");
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
