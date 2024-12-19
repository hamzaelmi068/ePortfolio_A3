package helmi_a3.ePortfolio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Hamza Elmi
 * student ID: 1305966
 * 
 * Compile like so:
 * cd Assignment3 , then cd helmi_a3
 * Compile: javac ePortfolio/*.java
 * Run: java ePortfolio.PortfolioGUI
 * javadoc: javadoc -d javadocA3 ePortfolio
 */

/**
 * * PortfolioGUI is the main GUI class for the ePortfolio application. * It
 * creates and manages different panels and allows switching between them using
 * a menu.
 */
public class PortfolioGUI extends JFrame {
    /**
     * Private field representing the main portfolio being managed.
     */
    private Portfolio portfolio;

    /**
     * Panel for buying new investments.
     */
    private BuyInvestmentPanel buyInvestmentPanel;

    /**
     * Panel for selling investments.
     */
    private SellPanel sellInvestmentPanel;

    /**
     * Panel for updating investment information.
     */
    private updatePanel updateInvestmentPanel;

    /**
     * Panel for searching investments.
     */
    private searchPanel searchInvestmentPanel;

    /**
     * Panel displaying the welcome screen.
     */
    private WelcomePanel welcomePanel;

    /**
     * Panel for calculating gains.
     */
    private getGainPanel gainPanel;

    /**
     * Layout manager for switching between different panels.
     */
    private CardLayout cardLayout;

    /**
     * Main panel containing all other panels.
     */
    private JPanel mainPanel;

    /**
     * Constructs a PortfolioGUI object and initializes the GUI components.
     */
    public PortfolioGUI() {
        portfolio = new Portfolio();
        setTitle("ePortfolio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
    }

    /**
     * Initializes the GUI components, including the panels and menu items.
     * Adds the panels to the main panel with a CardLayout and sets up the menu.
     */
    private void initializeComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize panels
        welcomePanel = new WelcomePanel();
        buyInvestmentPanel = new BuyInvestmentPanel(portfolio);
        sellInvestmentPanel = new SellPanel(portfolio);
        gainPanel = new getGainPanel(portfolio);
        updateInvestmentPanel = new updatePanel(portfolio);
        searchInvestmentPanel = new searchPanel(portfolio); // Added missing initialization

        // Add panels to main panel with card layout
        mainPanel.add(welcomePanel, "Welcome");
        mainPanel.add(buyInvestmentPanel, "Buy");
        mainPanel.add(sellInvestmentPanel, "Sell");
        mainPanel.add(gainPanel, "Gain");
        mainPanel.add(updateInvestmentPanel, "Update");
        mainPanel.add(searchInvestmentPanel, "Search"); // Fixed card name to match menu item

        // Create the menu
        JMenuBar menuBar = new JMenuBar();
        JMenu commandsMenu = new JMenu("Commands");

        // Menu items
        JMenuItem buyMenuItem = new JMenuItem("Buy");
        JMenuItem sellMenuItem = new JMenuItem("Sell");
        JMenuItem updateMenuItem = new JMenuItem("Update");
        JMenuItem getGainMenuItem = new JMenuItem("Get Gain");
        JMenuItem searchMenuItem = new JMenuItem("Search");
        JMenuItem quitMenuItem = new JMenuItem("Quit");

        // Add action listeners for menu items
        buyMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Buy"));
        // sell
        sellMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Sell"));
        // update
        updateMenuItem.addActionListener(e -> {
            mainPanel.remove(updateInvestmentPanel);
            updateInvestmentPanel = new updatePanel(portfolio);
            mainPanel.add(updateInvestmentPanel, "Update");
            ;
            cardLayout.show(mainPanel, "Update");
            updateInvestmentPanel.revalidate();
        });

        getGainMenuItem.addActionListener(e -> {
            gainPanel.displayGains(); // Update the gains when switching to this panel
            cardLayout.show(mainPanel, "Gain");
        });
        searchMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Search")); // Fixed card name
        quitMenuItem.addActionListener(e -> System.exit(0));

        // Add menu items to the menu
        commandsMenu.add(buyMenuItem);
        commandsMenu.add(sellMenuItem);
        commandsMenu.add(updateMenuItem);
        commandsMenu.add(getGainMenuItem);
        commandsMenu.add(searchMenuItem);
        commandsMenu.add(quitMenuItem);

        menuBar.add(commandsMenu);
        setJMenuBar(menuBar);

        // Add main panel to the frame
        add(mainPanel);

        // Set initial panel
        cardLayout.show(mainPanel, "Welcome");
    }

    /**
     * Main entry point for the Portfolio application.
     * 
     * @param args command-line arguments passed to the application
     *             (not used in this specific implementation)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PortfolioGUI().setVisible(true));
    }
}