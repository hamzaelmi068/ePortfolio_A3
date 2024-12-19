package helmi_a3.ePortfolio;

/**
 * This class represents a mutual fund investment that is part of a portfolio.
 * It is a subclass and inherits from the Investment superclass.
 * It contains the mutual fund's name, symbol, quantity, price, and book value.
 * This class provides methods to access and modify these attributes.
 */
public class MutualFund extends Investment {

    /**
     * Constructor for creating a new MutualFund object.
     *
     * @param symbol    the symbol of the mutual fund
     * @param name      the name of the mutual fund
     * @param quantity  the quantity of the mutual fund
     * @param price     the price of the mutual fund
     * @param bookValue the book value of the mutual fund
     * @throws IllegalArgumentException if any input parameters are invalid
     */
    public MutualFund(String name, String symbol, int quantity, double price, double bookValue)
            throws IllegalArgumentException {
        super(name, symbol, quantity, price, bookValue);
        calculateBookValue();
    }

    /**
     * Calculates the book value of the mutual fund.
     * For mutual funds, the book value is simply the quantity multiplied by the
     * price,
     * as there are no additional purchase fees.
     */
    @Override
    public void calculateBookValue() {
        setBookValue(getQuantity() * getPrice()); // no fee for mutualfunds
    }

    /**
     * Calculates the payment received when selling a portion of the mutual fund.
     * Includes a redemption fee of $45.00.
     *
     * @param quantitySold the number of mutual fund units being sold
     * @return the total payment received after subtracting the redemption fee
     */
    @Override
    public double calculatePaymentReceived(int quantitySold) {
        return quantitySold * getPrice() - 45.00; // redemption fee for mutualfund
    }

    /**
     * Calculates the gain or loss from selling a portion of the mutual fund.
     * The gain is calculated by subtracting the proportional book value of the
     * sold quantity from the payment received.
     *
     * @param quantitySold the number of mutual fund units being sold
     * @return the financial gain (or loss) from the sale
     */
    @Override
    public double calculateGain(int quantitySold) {
        double payment = calculatePaymentReceived(quantitySold);
        double bookValueSold = (getBookValue() / getQuantity()) * quantitySold;
        return payment - bookValueSold;
    }

    /**
     * Updates the price of the mutual fund and recalculates the book value.
     *
     * @param newPrice the new price of the mutual fund
     * @throws IllegalArgumentException if the new price is invalid
     */
    @Override
    public void updatePrice(double newPrice) throws IllegalArgumentException {
        setPrice(newPrice);
        calculateBookValue();
    }

    /**
     * Provides a string representation of the mutual fund.
     *
     * @return a string containing the mutual fund's details including
     *         symbol, name, quantity, price, and book value
     */
    @Override
    public String toString() {
        return "MutualFund{" + "symbol='" + getSymbol() + '\'' + ", name='" + getName() + '\'' + ", quantity="
                + getQuantity() + ", price=" + getPrice() + ", bookValue=" + getBookValue() + '}';
    }
}