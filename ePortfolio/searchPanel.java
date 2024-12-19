package helmi_a3.ePortfolio;

import javax.swing.*;
import java.awt.*;

/**
 * searchPanel is a JPanel that allows users to search for investments
 * in their portfolio based on various criteria.
 */
public class searchPanel extends JPanel {
    /**
     * Private field representing the portfolio being searched.
     */
    private Portfolio portfolio;

    /**
     * Text field for entering the investment symbol to search.
     */
    private JTextField symbolField;

    /**
     * Text field for entering name keywords to search.
     */
    private JTextField nameKeywordsField;

    /**
     * Text field for entering the low price range for searching.
     */
    private JTextField lowPriceField;

    /**
     * Text field for entering the high price range for searching.
     */
    private JTextField highPriceField;

    /**
     * Text area for displaying search results or messages.
     */
    private JTextArea messageArea;

    /**
     * Constructs a searchPanel object and initializes its components.
     *
     * @param portfolio The portfolio to search investments in
     */
    public searchPanel(Portfolio portfolio) {
        this.portfolio = portfolio;
        initializeComponents();
    }

    /**
     * Initializes the components of the searchPanel.
     * Sets up the layout, labels, text fields, buttons, and adds them to the panel.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Create and add the label at the top
        JLabel searchLabel = new JLabel("Searching Investments");
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        add(searchLabel, BorderLayout.NORTH);

        // Create the form panel
        JPanel searchFormPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        // Add components to the form panel
        searchFormPanel.add(new JLabel("Symbol"));
        symbolField = new JTextField();
        searchFormPanel.add(symbolField);

        searchFormPanel.add(new JLabel("Name Keywords"));
        nameKeywordsField = new JTextField();
        searchFormPanel.add(nameKeywordsField);

        searchFormPanel.add(new JLabel("Low Price"));
        lowPriceField = new JTextField();
        searchFormPanel.add(lowPriceField);

        searchFormPanel.add(new JLabel("High Price"));
        highPriceField = new JTextField();
        searchFormPanel.add(highPriceField);

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton searchButton = new JButton("Search");
        JButton resetInvestmentButton = new JButton("Reset");
        buttonPanel.add(searchButton);
        buttonPanel.add(resetInvestmentButton);

        searchFormPanel.add(new JLabel()); // Dummy label to align the buttons
        searchFormPanel.add(buttonPanel);

        // Add the form panel to the main panel
        add(searchFormPanel, BorderLayout.CENTER);

        // Create and add the message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(messageArea); // Add scrolling
        messagePanel.add(new JLabel("Search Results:"), BorderLayout.NORTH);
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.SOUTH);

        // Add action listeners
        searchButton.addActionListener(e -> searchInvestments());
        resetInvestmentButton.addActionListener(e -> resetForm());
    }

    /**
     * Searches for investments based on the input criteria and displays the
     * results.
     */
    private void searchInvestments() {
        String symbol = symbolField.getText().trim();
        String keywords = nameKeywordsField.getText().trim();
        double lowPrice = -1;
        double highPrice = -1;

        try {
            if (!lowPriceField.getText().trim().isEmpty()) {
                lowPrice = Double.parseDouble(lowPriceField.getText().trim());
            }

            if (!highPriceField.getText().trim().isEmpty()) {
                highPrice = Double.parseDouble(highPriceField.getText().trim());
            }

            String results = portfolio.searchInvestments(symbol, keywords, lowPrice, highPrice);
            addMessage(results);
        } catch (NumberFormatException e) {
            addMessage("Invalid input for price. Please enter numeric values.");
        }
    }

    /**
     * Resets all input fields to their default empty state.
     */
    private void resetForm() {
        symbolField.setText("");
        nameKeywordsField.setText("");
        lowPriceField.setText("");
        highPriceField.setText("");
    }

    /**
     * Adds a message to the message area.
     *
     * @param message The message to add
     */
    private void addMessage(String message) {
        messageArea.setText(""); // Clear previous messages
        messageArea.append(message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}
