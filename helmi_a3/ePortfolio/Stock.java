package helmi_a3.ePortfolio;

/**
 * This subclass represents a stock investment that is part of a portfolio.
 * It contains the stock's name, symbol, quantity, price, and book value,
 * inherited from the superclass.
 * This class provides methods to access and modify these attributes.
 */

public class Stock extends Investment {

    /**
     * Constructor for creating a new Stock object.
     *
     * @param name      the name of the stock
     * @param symbol    the symbol of the stock
     * @param quantity  the quantity of the stock
     * @param price     the price of the stock
     * @param bookValue the book value of the stock
     * @throws IllegalArgumentException if any of the input parameters are invalid
     */
    public Stock(String name, String symbol, int quantity, double price, double bookValue)
            throws IllegalArgumentException {
        super(name, symbol, quantity, price, bookValue);
        calculateBookValue();
    }

    /**
     * Calculates the book value of the stock based on the current quantity and
     * price.
     * The book value is set as the quantity multiplied by the price, plus a
     * commission fee of $9.99.
     */
    @Override
    public void calculateBookValue() {
        setBookValue(getQuantity() * getPrice() + 9.99);
    }

    /**
     * Calculates the payment received from selling a specified quantity of the
     * stock.
     * The payment received is the quantity sold multiplied by the current price,
     * minus a commission fee of $9.99.
     *
     * @param quantitySold the quantity of the stock being sold
     * @return the payment received from the stock sale
     */
    @Override
    public double calculatePaymentReceived(int quantitySold) {
        return quantitySold * getPrice() - 9.99; // commission fee for stocks
    }

    /**
     * Calculates the gain from selling a specified quantity of the stock.
     * The gain is the payment received minus the book value of the quantity sold.
     *
     * @param quantitySold the quantity of the stock being sold
     * @return the gain from the stock sale
     */
    @Override
    public double calculateGain(int quantitySold) {
        double payment = calculatePaymentReceived(quantitySold);
        double bookValueSold = (getBookValue() / getQuantity()) * quantitySold;
        return payment - bookValueSold;
    }

    /**
     * Updates the price of the stock and recalculates the book value.
     *
     * @param newPrice the new price of the stock
     * @throws IllegalArgumentException if the new price is invalid
     */
    @Override
    public void updatePrice(double newPrice) throws IllegalArgumentException {
        setPrice(newPrice);
        calculateBookValue();
    }

    @Override
    public String toString() {
        return "Stock{" + "symbol='" + getSymbol() + '\'' + ", name='" + getName() + '\'' + ", quantity="
                + getQuantity() + ", price=" + getPrice() + ", bookValue=" + getBookValue() + '}';
    }
}