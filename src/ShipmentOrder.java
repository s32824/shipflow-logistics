public abstract class ShipmentOrder implements SummaryPrintable {
    protected String orderNumber;
    protected String customerName;
    protected double distanceKm;
    protected double baseFee;
    protected boolean insured;
    protected double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();
        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);
        this.lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.isEmpty() || distanceKm <= 0) {
            throw new IllegalArgumentException("Invalid order data");
        }
    }

    protected void validateSpecificRules() {
    }

    private double applyInsurance(double price) {
        return insured ? price * 1.07 : price; //adds 7% if insured
    }

    protected double applyBusinessDiscount(double price) {
        return price;
    }

    private void printProcessingResult() {
        System.out.println("Order " + orderNumber + " processed. Price: " + lastCalculatedPrice);
    }

    public String buildSummaryLine() {
        return String.format("Order: %s | Customer: %s | Type: %s | Price: %.2f", orderNumber, customerName, getShipmentType(), lastCalculatedPrice);
    }

    //abstract methods
    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();
}