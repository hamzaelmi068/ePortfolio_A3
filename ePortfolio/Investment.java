package helmi_a3.ePortfolio;

/**
 * This superclass represents the investment superclass that has all the
 * mutualfunds
 * and stocks as its subclasses of a portfolio.
 * It contains the stock's, mutualfunds, and all related attributes
 * This class provides methods to access and modify these attributes.
 */
public abstract class Investment {
    // private instance variables (atttrbutes)
    private String name;
    private String symbol;
    private int quantity;
    private double price;
    private double bookValue;

    // Constructor, special method, executed automatically when a new obj is
    // created, using it for our Stock Class
    /**
     * Constructor for creating a new Stock object
     * 
     * @param symbol    - the stocks symbol
     * @param name      - the stocks name
     * @param quantity  - the stocks quantity
     * @param price     - the price of the stock
     * @param bookValue - the book value of the stock
     */
    public Investment(String symbol, String name, int quantity, double price, double bookValue)
            throws IllegalArgumentException {
        // checking if the symbol is empty
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        // checking if the name is empty, if it is, we throw an exception
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty, try again.");
        }
        if (quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Quantity and price must be positive values.");
        }
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
    }

    /* mutator and accessor methods below, for investment super class */
    // these getters and setters allow us to retrieve and modify our attributes from
    // both superclass
    // and subclasses, which means we can use them to update and retrieve values of
    // the attributes: name, symbol, quantity, etc

    /**
     * Gets the name of the investment.
     *
     * @return the name of the investment
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the investment.
     *
     * @param name the new name of the investment
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Gets the symbol of the investment.
     *
     * @return the symbol of the investment
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol of the investment.
     *
     * @param symbol the new symbol of the investment
     */
    public void setSymbol(String symbol) throws IllegalArgumentException {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty.");
        }
        this.symbol = symbol;
    }

    /**
     * Gets the quantity of the investment.
     *
     * @return the quantity of the investment
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the investment.
     *
     * @param quantity the new quantity of the investment
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive value.");
        }
        this.quantity = quantity;
    }

    /**
     * Gets the price of the investment.
     *
     * @return the price of the investment
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the investment.
     *
     * @param price the new price of the investment
     */
    public void setPrice(double price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
        this.price = price;
    }

    /**
     * Gets the book value of the investment.
     *
     * @return the book value of the investment
     */
    public double getBookValue() {
        return bookValue;
    }

    /**
     * Sets the book value of the investment.
     *
     * @param bookValue the new book value of the investment
     */
    public void setBookValue(double bookValue) throws IllegalArgumentException {
        if (bookValue < 0) {
            throw new IllegalArgumentException("Book value cannot be negative.");
        }
        this.bookValue = bookValue;
    }

    // Abstract methods to be implemented in my subclasses Stock and MutualFund
    /**
     * Calculates the book value of the investment.
     */
    public abstract void calculateBookValue();

    /**
     * Calculates the payment received from selling a quantity of the investment.
     *
     * @param quantitySold the quantity of the investment sold
     * @return the payment received from selling the quantity
     */
    public abstract double calculatePaymentReceived(int quantitySold);

    /**
     * Calculates the gain from selling a quantity of the investment.
     *
     * @param quantitySold the quantity of the investment sold
     * @return the gain from selling the quantity
     */
    public abstract double calculateGain(int quantitySold);

    /**
     * Updates the price of the investment.
     *
     * @param newPrice the new price of the investment
     */
    public abstract void updatePrice(double newPrice);

    @Override
    public String toString() {
        return "Investment{symbol='" + symbol + "', name='" + name + "', quantity=" + quantity + ", price=" + price
                + ", bookValue=" + bookValue + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Investment other = (Investment) obj;
        return symbol.equals(other.symbol);
    }
}
