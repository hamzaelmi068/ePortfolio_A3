package helmi_a3.ePortfolio;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The class below represents a Portfolio that will manage stocks and
 * mutualfunds
 */
public class Portfolio {
    // array list for investments below
    private ArrayList<Investment> investments;
    // using hashmap as an index to to stor the keywords
    private HashMap<String, ArrayList<Integer>> nameIndex;

    // Constructor for objects / Portfolio below
    /**
     * This Constructor for new Portfolio object
     * This initializes investments arraylist and nameindex arraylist for the
     * hashmap
     */
    public Portfolio() {
        investments = new ArrayList<>();
        nameIndex = new HashMap<>();
    }

    // print menu method, to make our main more organized
    private static void printMenu() {
        System.out.println("(Option 1): Buy - Check if the investment exists, update or create new");
        System.out.println("(Option 2): Sell - Check if the investment exists and has enough quantity");
        System.out.println("(Option 3): Update - Go through all investments and update prices ");
        System.out.println("(Option 4): Get Gain - Calculate total gain for all investments");
        System.out.println(
                "(Option 5): Search - Implement search functionality based on symbol, keywords, and price range");
        System.out.println("(Option 6): Exit the program");
        System.out.println("Enter your choice: ");
    }

    // main method, entry point of program, with command loop and menu options
    /**
     * The main method is the entry point for my program.
     * It initializes a new Portfolio and a Scanner for user input.
     * 
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage for the program should be: java Portfolio <filename1>");
            return;
        }

        String fileName = args[0];
        Portfolio ePortfolio = new Portfolio();
        Scanner scanner = new Scanner(System.in);

        // loading investments from a file
        ePortfolio.loadFromFile(fileName);

        String userInput;
        boolean shouldExit = false;

        do {
            // printing the menu
            printMenu();
            // scanning the users choice below
            userInput = scanner.nextLine().toLowerCase().trim();

            switch (userInput) {
                case "1":
                case "buy":
                case "b":
                    // Prompting user for input values
                    System.out.println("Enter the type of investment you'd like to choose (Stock, MutualFund): ");
                    String investmentChoice = scanner.nextLine().toLowerCase();

                    System.out.println("Enter the symbol of your investment: ");
                    String investmentSymbol = scanner.nextLine().toUpperCase();

                    System.out.println("Enter the name of the investment: ");
                    String name = scanner.nextLine().trim();

                    System.out.println("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.println("Enter the price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    // Call the buyInvestment method with the collected parameters
                    ePortfolio.buyInvestment(investmentChoice, investmentSymbol, name, quantity, price);
                    break;
                case "2":
                case "sell":
                    System.out.println("Enter the symbol of your investment: ");
                    investmentSymbol = scanner.nextLine().toUpperCase();

                    System.out.println("Enter the quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.println("Enter the price: ");
                    price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    ePortfolio.sellInvestment(investmentSymbol, quantity, price);
                    break;
                case "3":
                case "update":
                case "u":
                    // ePortfolio.updateInvestmentPrice(scanner);
                    break;
                case "4":
                case "gain":
                case "g":
                    double totalGain = ePortfolio.getTotalGain();
                    String individualGains = ePortfolio.getIndividualGains();
                    System.out.println("Total Gain: $" + String.format("%.2f", totalGain));
                    System.out.println("Individual Gains:\n" + individualGains);
                    break;
                case "5":
                case "search":
                    System.out.println("Enter the search criteria below.");

                    System.out.println("Enter the symbol: ");
                    String symbolInput = scanner.nextLine().trim().toUpperCase();

                    System.out.println("Enter the keywords: ");
                    String keywordSearch = scanner.nextLine().trim().toLowerCase();

                    System.out.println("Price range (min max): ");
                    String priceRange = scanner.nextLine().trim();
                    double lowPrice = -1;
                    double highPrice = -1;

                    if (!priceRange.isEmpty()) {
                        String[] prices = priceRange.split("\\s+");
                        if (prices.length == 2) {
                            try {
                                lowPrice = Double.parseDouble(prices[0]);
                                highPrice = Double.parseDouble(prices[1]);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid price range format.");
                                break;
                            }
                        } else {
                            System.out.println("Invalid price range format. Please enter two numeric values.");
                            break;
                        }
                    }

                    String searchResults = ePortfolio.searchInvestments(symbolInput, keywordSearch, lowPrice,
                            highPrice);
                    System.out.println("Search Results:\n" + searchResults);
                    break;
                case "6":
                case "quit":
                case "q":
                    System.out.println("Exiting program...");
                    shouldExit = true;
                    // saving investments before exiting
                    ePortfolio.saveFromFile(fileName);
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }

        } while (!shouldExit);

        // closing the scanner
        scanner.close();
    }

    /**
     * Loads investment data from a specified file and populates the portfolio.
     *
     * @param fileName the name of the file containing investment data
     */
    private void loadFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            // Initialize variables to store investment attributes
            String type = "";
            String symbol = "";
            String name = "";
            int quantity = 0;
            double price = 0.0, bookValue = 0.0;

            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.contains("=")) {
                    // Split the line into key and value
                    String[] parts = line.split("=");
                    String key = parts[0].trim();
                    // Remove quotes from value
                    String value = parts[1].trim().replace("\"", "").trim();

                    // Assign values to the corresponding variables based on the key
                    switch (key) {
                        case "type":
                            type = value;
                            break;
                        case "symbol":
                            symbol = value;
                            break;
                        case "name":
                            name = value;
                            break;
                        case "quantity":
                            quantity = Integer.parseInt(value);
                            break;
                        case "price":
                            price = Double.parseDouble(value);
                            break;
                        case "bookValue":
                            bookValue = Double.parseDouble(value);
                            break;
                    }

                } else if (line.isEmpty() && !type.isEmpty()) {
                    // Create and add an investment if line is empty and type is set
                    Investment currentInvestment = null;
                    if (type.equals("stock")) {
                        currentInvestment = new Stock(symbol, name, quantity, price, bookValue);
                    } else if (type.equals("mutualfund")) {
                        currentInvestment = new MutualFund(symbol, name, quantity, price, bookValue);
                    }
                    // Add the created investment to the portfolio
                    if (currentInvestment != null) {
                        investments.add(currentInvestment);
                        addToNameIndex(name, investments.size() - 1);
                        System.out.println("Loaded investment: " + currentInvestment); // Debugging print statement
                    }

                    // Reset for the next investment
                    type = "";
                    symbol = "";
                    name = "";
                    quantity = 0;
                    price = 0.0;
                    bookValue = 0.0;
                }
            }

            // Ensure the last investment is added if file doesn't end with a blank line
            if (!type.isEmpty()) {
                Investment currentInvestment = null;
                if (type.equals("stock")) {
                    currentInvestment = new Stock(symbol, name, quantity, price, bookValue);
                } else if (type.equals("mutualfund")) {
                    currentInvestment = new MutualFund(symbol, name, quantity, price, bookValue);
                }
                // adding the created investment to the portoflio
                if (currentInvestment != null) {
                    investments.add(currentInvestment);
                    addToNameIndex(name, investments.size() - 1);
                    System.out.println("Loaded investment: " + currentInvestment); // Debugging print statement
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found: " + fileName);
        }
    }

    /**
     * saves the current portoflio of investments to a specific file
     *
     * @param fileName the name of the file containing investment data
     */
    private void saveFromFile(String fileName) {
        try (PrintWriter writing = new PrintWriter(new FileWriter(fileName))) {
            // Iterate over each investment in the portfolio
            for (Investment investing : investments) {
                // Determine the type of investment (stock or mutual fund)
                String instance;
                if (investing instanceof MutualFund) {
                    instance = "mutualfund";
                } else {
                    instance = "stock";
                }
                writing.println("type = \"" + instance + "\"");

                // Write the remaining investment details
                writing.println("symbol = \"" + investing.getSymbol() + "\"");
                writing.println("name = \"" + investing.getName() + "\"");
                writing.println("quantity = \"" + investing.getQuantity() + "\"");
                writing.println("price = \"" + String.format("%.2f", investing.getPrice()) + "\"");
                writing.println("bookValue = \"" + String.format("%.2f", investing.getBookValue()) + "\"");

                // Add blank line between investments
                writing.println();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }

    // Task 1: Buying method: Check if the investment exists, update or create new
    /**
     * Buys an investment (Stock or MutualFund) and adds it to the portfolio.
     * 
     * @param investmentChoice the type of investment, either "stock" or
     *                         "mutualfund"
     * @param investmentSymbol the unique symbol identifying the investment
     * @param name             the name of the investment
     * @param quantity         the number of investment units to purchase
     * @param price            the price per unit of the investment
     * @throws IllegalArgumentException if the input parameters are invalid or
     *                                  if an investment with the same symbol
     *                                  already exists
     */
    // Buying method: Check if the investment exists, update or create new
    public void buyInvestment(String investmentChoice, String investmentSymbol, String name, int quantity,
            double price) throws IllegalArgumentException {
        try {
            // exception check for investment symbol
            if (investmentSymbol == null || investmentSymbol.trim().isEmpty()) {
                throw new IllegalArgumentException("investment Symbol cant be empty");
            }
            // exception check for name
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cant be empty.");
            }

            // exception check for quantity and price
            if (quantity <= 0 || price <= 0) {
                throw new IllegalArgumentException("Quantity and price must be positive values.");
            }

            // Check if an investment with the same symbol already exists
            for (Investment investment : investments) {
                if (investment.getSymbol().equalsIgnoreCase(investmentSymbol)) {
                    throw new IllegalArgumentException("An investment with this symbol already exists.");
                }
            }

            // Add new investment
            Investment brandNewInvestment;
            if (investmentChoice.equalsIgnoreCase("stock")) {
                brandNewInvestment = new Stock(investmentSymbol, name, quantity, price, 0.0);
            } else if (investmentChoice.equalsIgnoreCase("mutualfund")) {
                brandNewInvestment = new MutualFund(investmentSymbol, name, quantity, price, 0.0);
            } else {
                throw new IllegalArgumentException("Invalid investment type, please enter 'stock' or 'mutualfund'.");
            }
            brandNewInvestment.calculateBookValue();
            investments.add(brandNewInvestment);
            int newPosition = investments.size() - 1;
            addToNameIndex(brandNewInvestment.getName(), newPosition);

            System.out.println("Added new investment: " + brandNewInvestment);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter numeric values for quantity and price.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Methods for storing the positions of investments in arraylist
    // method to add investment to index
    /**
     * Adds keywords from the investment name to the name index, mapping each
     * keyword
     * to the position of the investment in the list.
     *
     * @param name     the name of the investment
     * @param position the position of the investment in the list
     */
    private void addToNameIndex(String name, int position) {
        // Split the name into individual keywords
        String[] keywords = name.toLowerCase().split("\\s+");

        // Iterate through each keyword
        for (String keyword : keywords) {
            // Retrieve the list of positions for the keyword or create a new list if not
            // present
            ArrayList<Integer> positions = nameIndex.getOrDefault(keyword, new ArrayList<>());
            // Add the current position to the list
            positions.add(position);
            // Update the map with the new list of positions
            nameIndex.put(keyword, positions);
        }
    }

    // Method to update positions after deletion
    /**
     * Updates all positions in the name index to account for a deleted investment.
     *
     * @param deletedPosition the position of the investment that was deleted
     */
    private void updatePositionsAfterDeletion(int deletedPosition) {
        // Iterate through all position lists in the name index
        for (ArrayList<Integer> positions : nameIndex.values()) {
            // removing the deleted position from the list
            positions.removeIf(pos -> pos == deletedPosition);
            for (int i = 0; i < positions.size(); i++) {
                if (positions.get(i) > deletedPosition) {
                    positions.set(i, positions.get(i) - 1);
                }
            }
        }
        // Remove any empty lists
        nameIndex.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

    // Task 2: Selling Investments: Check if investment exists and has enough
    // quantity
    /**
     * Sells a specified quantity of an investment from the portfolio.
     * 
     * @param investmentSymbol the symbol of the investment to sell
     * @param quantity         the number of units to sell
     * @param price            the selling price per unit
     * @throws IllegalArgumentException if the quantity or price is invalid
     */
    public void sellInvestment(String investmentSymbol, int quantity, double price) throws IllegalArgumentException {
        try {
            if (quantity <= 0 || price <= 0) {
                throw new IllegalArgumentException("Quantity and price must be positive values.");
            }

            boolean investmentFound = false;
            for (Investment investment : investments) {
                if (investment.getSymbol().equalsIgnoreCase(investmentSymbol)) {
                    investmentFound = true;
                    if (investment.getQuantity() >= quantity) {
                        double saleProceeds;
                        if (investment instanceof Stock) {
                            saleProceeds = price * quantity - 9.99; // commission fee
                        } else {
                            saleProceeds = price * quantity - 45.00; // fee for mutual fund
                        }

                        double bookValueSold = investment.getBookValue()
                                * ((double) quantity / investment.getQuantity());
                        double gainLoss = saleProceeds - bookValueSold;

                        addMessage(String.format("Sale Proceeds (Payment): %.2f", saleProceeds));
                        addMessage(String.format("Gain/Loss: %.2f", gainLoss));

                        investment.setQuantity(investment.getQuantity() - quantity);
                        if (investment.getQuantity() > 0) {
                            double remainingQuantity = investment.getQuantity();
                            investment.setBookValue(
                                    (investment.getBookValue() * remainingQuantity) / (remainingQuantity + quantity));
                        } else {
                            int position = investments.indexOf(investment);
                            investments.remove(investment);
                            updatePositionsAfterDeletion(position);
                            addMessage("Investment has been completely sold and removed from the portfolio.");
                        }
                        addMessage(String.format("Updated Quantity after sale: %d", investment.getQuantity()));
                    } else {
                        addMessage("Insufficient quantity for sale.");
                    }
                    break;
                }
            }
            if (!investmentFound) {
                addMessage("Investment not found.");
            }
        } catch (IllegalArgumentException e) {
            addMessage("Error: " + e.getMessage());
        }
    }

    private void addMessage(String message) {
        System.out.println(message);
    }

    /**
     * Array list for getting the investments
     * 
     * @return this returns an investments array list
     */
    public ArrayList<Investment> getInvestments() {
        return investments;
    }

    /**
     * Updates the price of an investment at the specified index in the portfolio.
     * Recalculates the book value after updating the price.
     * 
     * @param index    the position of the investment in the investments list
     * @param newPrice the updated price for the investment
     */
    public void updateInvestmentPrice(int index, double newPrice) {
        if (investments.isEmpty()) {
            System.out.println("No investments to update");
            return;
        }

        if (index >= 0 && index < investments.size()) {
            Investment investment = investments.get(index);
            investment.updatePrice(newPrice);
            investment.calculateBookValue();
            System.out.println("Updated investment: " + investment);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Calculates and returns the total gain across all investments in the
     * portfolio.
     * 
     * @return the total financial gain of all investments, combining gains from
     *         both stocks and mutual funds
     */
    public double getTotalGain() {
        double totalGain = 0.0;
        for (Investment investment : investments) {
            double gain;

            if (investment instanceof Stock) {
                gain = investment.calculateGain(investment.getQuantity());
            } else if (investment instanceof MutualFund) {
                gain = investment.calculateGain(investment.getQuantity());
            } else {
                System.out.println("Unknown investment type: " + investment.getClass().getSimpleName());
                continue;
            }

            totalGain += gain;
        }

        return totalGain;
    }

    /**
     * Generates a formatted string of individual gains for each investment.
     * 
     * @return a string containing the name, symbol, and gain for each investment,
     *         with each investment on a new line
     */
    public String getIndividualGains() {
        StringBuilder gains = new StringBuilder();
        for (Investment investment : investments) {
            double gain;

            if (investment instanceof Stock) {
                gain = investment.calculateGain(investment.getQuantity());
            } else if (investment instanceof MutualFund) {
                gain = investment.calculateGain(investment.getQuantity());
            } else {
                continue;
            }

            gains.append("Investment: ").append(investment.getName())
                    .append(", Symbol: ").append(investment.getSymbol())
                    .append(", Gain: $").append(String.format("%.2f", gain))
                    .append("\n");
        }
        return gains.toString();
    }

    /**
     * Searches for investments based on specified search criteria.
     * 
     * @param symbolInput   the investment symbol to filter by (case-insensitive)
     * @param keywordSearch keywords to match against investment names
     * @param lowPrice      the lower bound of the price range to search
     * @param highPrice     the upper bound of the price range to search
     * @return a string containing details of matching investments, or a message
     *         if no investments are found
     */
    public String searchInvestments(String symbolInput, String keywordSearch, double lowPrice, double highPrice) {
        ArrayList<Investment> results = new ArrayList<>(investments);

        // Search by keywords using nameIndex
        if (!keywordSearch.isEmpty()) {
            String[] keywords = keywordSearch.split("\\s+");
            Set<Integer> matchedPositions = new HashSet<>();

            for (String keyword : keywords) {
                ArrayList<Integer> positions = nameIndex.get(keyword);
                if (positions != null) {
                    if (matchedPositions.isEmpty()) {
                        matchedPositions.addAll(positions);
                    } else {
                        matchedPositions.retainAll(positions);
                    }
                }
            }
            // Filter results based on matched positions
            if (!matchedPositions.isEmpty()) {
                results.removeIf(investment -> !matchedPositions.contains(investments.indexOf(investment)));
            } else {
                results.clear(); // No matches found for keywords
            }
        }

        // Filter by symbol if provided
        if (!symbolInput.isEmpty()) {
            results.removeIf(investment -> !investment.getSymbol().equalsIgnoreCase(symbolInput));
        }

        // Filter by price range if provided
        if (lowPrice >= 0 && highPrice >= 0) {
            results.removeIf(investment -> investment.getPrice() < lowPrice || investment.getPrice() > highPrice);
        }

        // Display the results
        if (results.isEmpty()) {
            return "No matching investments were found.";
        } else {
            StringBuilder resultString = new StringBuilder();
            for (Investment investment : results) {
                resultString.append(investment).append("\n");
            }
            return resultString.toString();
        }
    }
}
