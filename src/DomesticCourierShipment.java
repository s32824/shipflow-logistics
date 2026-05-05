public class DomesticCourierShipment extends ShipmentOrder {
    private double packageWeightKg;
    private boolean weekendDelivery;

    public DomesticCourierShipment(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured, double packageWeightKg, boolean weekendDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.packageWeightKg = packageWeightKg;
        this.weekendDelivery = weekendDelivery;
    }

    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + (distanceKm * 1.20);
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = packageWeightKg * 4.00;
        if (weekendDelivery) {
            fee += 25; //additional 25 zloty for weekends
        }
        return fee;
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        //if distance is at least 300 km, reduce price by 5%
        if (distanceKm >= 300) {
            return price * 0.95;
        }
        return price;
    }
}