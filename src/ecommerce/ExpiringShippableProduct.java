package ecommerce;

import java.util.Date;

public class ExpiringShippableProduct extends ExpiringProduct implements Shippable {
    private final double weight;

    public ExpiringShippableProduct(String name, double price, int qty,
                                    Date expiryDate, double weight) {
        super(name, price, qty, expiryDate);
        this.weight = weight;
    }

    @Override public double getWeight() { return weight; }
}
